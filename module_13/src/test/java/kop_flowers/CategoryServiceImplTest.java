package kop_flowers;

import kz.kop_flowers.model.FlowerMapper;
import kz.kop_flowers.model.dto.CategoryDto;
import kz.kop_flowers.model.entity.Category;
import kz.kop_flowers.model.exception.CategoryNotFoundException;
import kz.kop_flowers.repository.CategoryRepository;
import kz.kop_flowers.service.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private FlowerMapper mapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void testGetCategoryById_success() {
        // Arrange
        Integer categoryId = 1;
        Category category = Category.builder()
                .id(categoryId)
                .name("8 марта")
                .build();

        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        Category result = categoryService.getCategoryById(categoryId);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(categoryId, result.getId());
        Assertions.assertEquals("8 марта", result.getName());
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    public void testGetCategoryById_notFound() {
        // Arrange
        Integer categoryId = 999;
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.getCategoryById(categoryId);
        });
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    public void testGetCategoryDtoById_success() {
        // Arrange
        Integer categoryId = 1;
        Category category = Category.builder()
                .id(categoryId)
                .name("8 марта")
                .build();
        CategoryDto categoryDto = CategoryDto.builder()
                .id(categoryId)
                .name("8 марта")
                .build();

        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        Mockito.when(mapper.fromEntityToDto(category)).thenReturn(categoryDto);

        // Act
        CategoryDto result = categoryService.getCategoryDtoById(categoryId);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(categoryId, result.getId());
        Assertions.assertEquals("8 марта", result.getName());
        verify(categoryRepository, times(1)).findById(categoryId);
        verify(mapper, times(1)).fromEntityToDto(category);
    }

    @Test
    public void testGetCategoryDtoById_notFound() {
        // Arrange
        Integer categoryId = 999;
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.getCategoryDtoById(categoryId);
        });
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    public void testGetAllCategories_success() {
        // Arrange
        List<Category> categories = List.of(
                Category.builder().id(1).name("8 марта").build(),
                Category.builder().id(2).name("День рождения").build(),
                Category.builder().id(3).name("Свадьба").build()
        );

        List<CategoryDto> categoryDtos = List.of(
                CategoryDto.builder().id(1).name("8 марта").build(),
                CategoryDto.builder().id(2).name("День рождения").build(),
                CategoryDto.builder().id(3).name("Свадьба").build()
        );

        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        Mockito.when(mapper.fromEntityToDto(categories.get(0))).thenReturn(categoryDtos.get(0));
        Mockito.when(mapper.fromEntityToDto(categories.get(1))).thenReturn(categoryDtos.get(1));
        Mockito.when(mapper.fromEntityToDto(categories.get(2))).thenReturn(categoryDtos.get(2));

        // Act
        List<CategoryDto> result = categoryService.getAllCategories();

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("8 марта", result.get(0).getName());
        Assertions.assertEquals("День рождения", result.get(1).getName());
        Assertions.assertEquals("Свадьба", result.get(2).getName());
        verify(categoryRepository, times(1)).findAll();
        verify(mapper, times(3)).fromEntityToDto(any(Category.class));
    }

    @Test
    public void testGetAllCategories_empty() {
        // Arrange
        List<Category> emptyCategories = List.of();
        Mockito.when(categoryRepository.findAll()).thenReturn(emptyCategories);

        // Act
        List<CategoryDto> result = categoryService.getAllCategories();

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void testCreateCategory_success() {
        // Arrange
        CategoryDto inputDto = CategoryDto.builder()
                .name("Новый год")
                .build();

        Category savedCategory = Category.builder()
                .id(1)
                .name("Новый год")
                .build();

        CategoryDto outputDto = CategoryDto.builder()
                .id(1)
                .name("Новый год")
                .build();

        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);
        Mockito.when(mapper.fromEntityToDto(savedCategory)).thenReturn(outputDto);

        // Act
        CategoryDto result = categoryService.createCategory(inputDto);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("Новый год", result.getName());
        verify(categoryRepository, times(1)).save(any(Category.class));
        verify(mapper, times(1)).fromEntityToDto(savedCategory);
    }

    @Test
    public void testCreateCategory_withNullName() {
        // Arrange
        CategoryDto inputDto = CategoryDto.builder()
                .name(null)
                .build();

        Category savedCategory = Category.builder()
                .id(1)
                .name(null)
                .build();

        CategoryDto outputDto = CategoryDto.builder()
                .id(1)
                .name(null)
                .build();

        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);
        Mockito.when(mapper.fromEntityToDto(savedCategory)).thenReturn(outputDto);

        // Act
        CategoryDto result = categoryService.createCategory(inputDto);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertNull(result.getName());
        verify(categoryRepository, times(1)).save(any(Category.class));
        verify(mapper, times(1)).fromEntityToDto(savedCategory);
    }
}

