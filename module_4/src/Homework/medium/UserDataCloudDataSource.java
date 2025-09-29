package Homework.medium;

public class UserDataCloudDataSource implements DataSource<UserData> {
    @Override
    public UserData getData() {
        return new UserData(3, "John Doe", "john.doe@example.com");
    }
}
