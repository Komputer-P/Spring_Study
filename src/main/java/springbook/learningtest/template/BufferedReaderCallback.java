package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.IOException;

interface LineCallBack<T> {
    T doSomethingWithLine(String line, T value) throws IOException;
}
