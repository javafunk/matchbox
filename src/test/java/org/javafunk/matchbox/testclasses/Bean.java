package org.javafunk.matchbox.testclasses;

import static java.lang.String.format;

public class Bean {
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;

    public Bean(String attribute1, String attribute2, String attribute3, String attribute4) {
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
    }

    public static Bean bean(String attribute1, String attribute2, String attribute3, String attribute4) {
        return new Bean(attribute1, attribute2, attribute3, attribute4);
    }

    public String getAttribute1() {
        return attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    @Override
    public String toString() {
        return format("Bean<attribute1=<%s>, attribute2=<%s>, attribute3=<%s>, attribute4=<%s>, >", attribute1, attribute2, attribute3, attribute4);
    }
}
