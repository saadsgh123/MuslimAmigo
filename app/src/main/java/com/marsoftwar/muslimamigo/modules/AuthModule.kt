package com.marsoftwar.muslimamigo.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.android.gms.auth.api.identity.Identity
import com.marsoftwar.muslimamigo.authentication.GoogleAuthUiClient
import com.marsoftwar.muslimamigo.data.local.room.Database
import com.marsoftwar.muslimamigo.data.local.room.DouaeDao
import com.marsoftwar.muslimamigo.data.local.room.DouaeRepoImpl
import com.marsoftwar.muslimamigo.data.repository.DouaeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideGoogleAuthUiClient(
        @ApplicationContext context: Context
    ) : GoogleAuthUiClient {
        return GoogleAuthUiClient(
            Identity.getSignInClient(context),
            context
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            "douae_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDouaeRepository(db: Database) : DouaeRepository {
        return DouaeRepoImpl(db.dao)
    }

}