package com.github.kenobasedow.popularmovies;

public interface AsyncTaskCompleteListener<T>
{
    public void onTaskComplete(T result);
}
