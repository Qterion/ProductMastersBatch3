package kop_flowers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.kop_flowers.controller.CategoryController;
import kz.kop_flowers.model.dto.CategoryDto;
import kz.kop_flowers.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        CategoryController controller = new CategoryController(categoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllCategories() throws Exception {
        // Arrange
        List<CategoryDto> categories = List.of(
                CategoryDto.builder().id(1).name("8 марта").build(),
                CategoryDto.builder().id(2).name("День рождения").build()
        );

        Mockito.when(categoryService.getAllCategories()).thenReturn(categories);

        // Act & Assert
        mockMvc.perform(get("/api/category/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("8 марта"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("День рождения"));

        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    void testGetAllCategories_empty() throws Exception {
        // Arrange
        List<CategoryDto> emptyCategories = List.of();
        Mockito.when(categoryService.getAllCategories()).thenReturn(emptyCategories);

        // Act & Assert
        mockMvc.perform(get("/api/category/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    void testCreateCategory() throws Exception {
        // Arrange
        CategoryDto requestDto = CategoryDto.builder()
                .name("Новый год")
                .build();

        CategoryDto responseDto = CategoryDto.builder()
                .id(1)
                .name("Новый год")
                .build();

        Mockito.when(categoryService.createCategory(Mockito.any(CategoryDto.class))).thenReturn(responseDto);

        // Act & Assert
        mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Новый год"));

        verify(categoryService, times(1)).createCategory(Mockito.any(CategoryDto.class));
    }

    @Test
    void testCreateCategory_withEmptyName() throws Exception {
        // Arrange
        CategoryDto requestDto = CategoryDto.builder()
                .name("")
                .build();

        CategoryDto responseDto = CategoryDto.builder()
                .id(2)
                .name("")
                .build();

        Mockito.when(categoryService.createCategory(Mockito.any(CategoryDto.class))).thenReturn(responseDto);

        // Act & Assert
        mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value(""));

        verify(categoryService, times(1)).createCategory(Mockito.any(CategoryDto.class));
    }
}

