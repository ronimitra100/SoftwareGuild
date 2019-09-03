package com.sg.functionalunittests;

/* Given a String name, e.g. "Bob", return a greeting of the
 * form "Hello Bob!".
 * sayHi("Bob") -> "Hello Bob!"
 * sayHi("Alice") -> "Hello Alice!"
 * sayHi("X") -> "Hello X!" 
 */
public class SayHi {

    public String sayHi(String name) {

        return ("Hello " + name + "!");
    }
}
