package Homework.medium;

public class Main {
    public static void main(String[] args) {
        DataSource<MyData> myDataDataSource = new Repository<>(
                new CachedDataSource<>(), new MyDataCloudDataSource());

        DataSource<GeoData> geoDataDataSource = new GeoRepository(
                new CachedDataSource<>(), new GeoDataCloudDataSource());

        DataSource<UserData> userDataDataSource = new UserRepository(
                new CachedDataSource<>(), new UserDataCloudDataSource());

        MyData myData = myDataDataSource.getData();
        GeoData geoData = geoDataDataSource.getData();
        UserData userData = userDataDataSource.getData();

        System.out.println(myData.toString());
        System.out.println(geoData.toString());
        System.out.println(userData.toString());

        // Test caching for UserData
        System.out.println("Getting UserData again to test caching:");
        userData = userDataDataSource.getData();
        System.out.println(userData.toString());
    }

}
