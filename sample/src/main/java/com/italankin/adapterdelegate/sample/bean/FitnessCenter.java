package com.italankin.adapterdelegate.sample.bean;

import com.italankin.adapterdelegate.sample.util.LoremIpsum;

import java.util.Random;

public class FitnessCenter {
    public final String name;
    public final String price;
    public final String description;

    public FitnessCenter() {
        LoremIpsum lipsum = new LoremIpsum().setCapitilize(true).setWordCount(3, 5);
        name = lipsum.toString();
        price = "$" + new Random().nextInt(10000);
        description = lipsum.setWordCount(10, 50).useSentences(true).setMinSentenceLength(4).toString();
    }
}
