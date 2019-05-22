
package com.main.eshop.util;

import java.io.InputStream;

/**
 *
 * @author eugenio.ferrari
 */
public class Image {
    private String name;
    private InputStream stream;
    
    /**
     * Constructor
     * @param name name
     * @param stream stream
     */
    public Image(String name, InputStream stream){
        this.name = name;
        this.stream = stream;
    }

    // GETTER SETTER
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }
    
    
}
