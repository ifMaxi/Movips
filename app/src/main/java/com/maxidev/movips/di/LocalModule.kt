package com.maxidev.movips.di

import android.content.Context
import androidx.room.Room
import com.maxidev.movips.data.local.MovipsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesDataBase(
        @ApplicationContext context: Context
    ): MovipsDataBase {

        return Room.databaseBuilder(
            context = context,
            klass = MovipsDataBase::class.java,
            name = "movips_data_base"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}