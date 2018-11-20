package com.company;

import com.company.core.Engine;
import com.company.core.StorageMaster;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Engine engine = new Engine(new StorageMaster());
        engine.run();
    }
}
