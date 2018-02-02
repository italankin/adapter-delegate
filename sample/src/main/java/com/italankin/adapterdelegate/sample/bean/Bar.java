package com.italankin.adapterdelegate.sample.bean;

import com.italankin.adapterdelegate.sample.util.LoremIpsum;

import java.util.Random;

public class Bar {
    public final String name;
    public final String price;
    public final String description;
    public final String address;

    public Bar() {
        LoremIpsum lipsum = new LoremIpsum().setCapitilizeEach(true).setWordCount(3, 5);
        name = lipsum.toString();
        price = "$" + new Random().nextInt(10000);
        address = lipsum.toString();
        description = lipsum.setWordCount(20, 50).useSentences(true).setMinSentenceLength(4).toString();
    }
}
