package com.italankin.adapterdelegate.sample.bean;

import com.italankin.adapterdelegate.sample.util.LoremIpsum;

public class Hotel {
    public final String name;
    public final String description;
    public final String address;

    public Hotel() {
        LoremIpsum lipsum = new LoremIpsum().setCapitilizeEach(true).setWordCount(3, 5);
        name = lipsum.toString();
        address = lipsum.toString();
        description = lipsum.setWordCount(20, 30).useSentences(true).setMinSentenceLength(4).toString();
    }
}
