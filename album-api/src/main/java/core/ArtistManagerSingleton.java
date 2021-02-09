package core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum ArtistManagerSingleton {

    INSTANCE;

    business.ArtistManagerImpl artistManagerImplementation;

    public void setArtistManagerImplementation(String className){
        try {
            Class<?> artistManagerImplementationClass = Class.forName(className);
            // Call parameterized constructor
            Class<?>[] type = {};
            //Get parameterized constructor
            Constructor<?> artistManagerConstructor = artistManagerImplementationClass.getConstructor(type);
            //Instantiate object
            artistManagerImplementation = (business.ArtistManagerImpl) artistManagerConstructor.newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println("Could not set Artist Manager Implementation");
            e.printStackTrace();
        }
    }

    public business.ArtistManagerImpl getArtistManagerImplementation(){
        return artistManagerImplementation;
    }
}
