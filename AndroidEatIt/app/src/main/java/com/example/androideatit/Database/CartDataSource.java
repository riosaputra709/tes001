package com.example.androideatit.Database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface CartDataSource {
    Flowable<List<CartItem>> getAllCart (String uid);

    Single<Integer> countItemInCart (String uid);

    Single<Long> sumPriceInCart (String uid);

    Single<CartItem> getItemInCart (String foodId, String uid);

    Completable insertOrReplaceAll(CartItem... cartItems);

    Single<Integer> updateCartItems(CartItem cartItem);

    Single<Integer> deleteCartItems(CartItem cartItem);

    Single<Integer> cleanCart (String uid);

    Single<CartItem> getItemWithAllOptionsInCart (String uid, String foodId, String foodAddon, String foodSize);
}
