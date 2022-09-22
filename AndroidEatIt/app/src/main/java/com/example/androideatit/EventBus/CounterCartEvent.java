package com.example.androideatit.EventBus;

public class CounterCartEvent {
    private boolean sucess;

    public CounterCartEvent(boolean sucess) {
        this.sucess = sucess;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }
}
