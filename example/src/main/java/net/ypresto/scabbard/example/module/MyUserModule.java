package net.ypresto.scabbard.example.module;

import net.ypresto.scabbard.example.object.UserInformationObject;

import dagger.Module;
import dagger.Provides;

@Module
public class MyUserModule {
    private final Integer userId;

    public MyUserModule() {
        this.userId = null;
    }

    public MyUserModule(int userId) {
        this.userId = userId;
    }

    @Provides
    public UserInformationObject provideUserBoundObject() {
        if (userId == null) throw new IllegalStateException("userId is not passed.");
        return new UserInformationObject(userId);
    }
}
