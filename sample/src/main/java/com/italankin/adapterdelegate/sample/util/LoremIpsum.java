package com.italankin.adapterdelegate.sample.util;

import java.util.Locale;
import java.util.Random;

public class LoremIpsum {

    public static final String SPACE_CHAR = " ";
    public static final String DOT_CHAR = ".";
    public static final String COMMA_CHAR = ",";
    public static final String PARAGRAPH_SEPARATOR = System.getProperty("line.separator");

    private static final String[] ENTRIES = {
            "lorem",
            "ipsum",
            "dolor",
            "sit",
            "amet",
            "consectetur",
            "adipiscing",
            "elit",
            "sed",
            "do",
            "eiusmod",
            "tempor",
            "incididunt",
            "ut",
            "labore",
            "et",
            "magna",
            "aliqua",
            "enim",
            "ad",
            "minim",
            "veniam",
            "quis",
            "nostrud",
            "exercitation",
            "ullamco",
            "laboris",
            "nisi",
            "aliquip",
            "ex",
            "ea",
            "commodo",
            "consequat",
            "duis",
            "aute",
            "irure",
            "reprehenderit",
            "in",
            "voluptate",
            "velit",
            "esse",
            "cillum",
            "dolore",
            "eu",
            "fugiat",
            "nulla",
            "pariatur",
            "excepteur",
            "sint",
            "occaecat",
            "cupidatat",
            "non",
            "proident",
            "sunt",
            "culpa",
            "qui",
            "officia",
            "deserunt",
            "mollit",
            "anim",
            "id",
            "est",
            "laborum"
    };

    private final Random mRandom;
    private int mWordCountMin = 1;
    private int mWordCountMax = 1;
    private int mMinSentenceLength = 5;
    private boolean mCapitilize = true;
    private boolean mUseSentences = false;
    private boolean mUseCommas = false;
    private boolean mUseParagraphs = false;
    private boolean mCapitilizeEach = false;
    private String mParagraphSeparator = PARAGRAPH_SEPARATOR;
    private String mSpaceSequence = SPACE_CHAR;
    private String mCommaSequence = COMMA_CHAR;
    private String mDotSequence = DOT_CHAR;

    public LoremIpsum() {
        this(new Random());
    }

    public LoremIpsum(Random random) {
        if (random == null) {
            throw new IllegalArgumentException("random == null");
        }
        mRandom = random;
    }

    public LoremIpsum setWordCount(int count) {
        return setWordCount(count, count);
    }

    public LoremIpsum setWordCount(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be < max!");
        }
        if (min <= 0 && max > 0) {
            throw new IllegalArgumentException("cannot set word count with min=" + min + " , max=" + max +
                    ": min cannot be <= 0, when max > 0");
        }
        mWordCountMin = min;
        mWordCountMax = max;
        return this;
    }

    public LoremIpsum setMinSentenceLength(int minLength) {
        mMinSentenceLength = minLength;
        return this;
    }

    public LoremIpsum setCapitilize(boolean capitilize) {
        mCapitilize = capitilize;
        return this;
    }

    public LoremIpsum setCapitilizeEach(boolean capitilizeEach) {
        mCapitilizeEach = capitilizeEach;
        return this;
    }

    public LoremIpsum useSentences(boolean useSentences) {
        mUseSentences = useSentences;
        return this;
    }

    public LoremIpsum useCommas(boolean useCommas) {
        mUseCommas = useCommas;
        return this;
    }

    public LoremIpsum useParagraphs(boolean useParagraphs) {
        mUseParagraphs = useParagraphs;
        return this;
    }

    public LoremIpsum spaceSequence(String spaceSequence) {
        if (spaceSequence == null) {
            throw new IllegalArgumentException("spaceSequence == null");
        }
        mSpaceSequence = spaceSequence;
        return this;
    }

    public LoremIpsum dotSequence(String dotSequence) {
        if (dotSequence == null) {
            throw new IllegalArgumentException("dotSequence == null");
        }
        mDotSequence = dotSequence;
        return this;
    }

    public LoremIpsum commaSequence(String commaSequence) {
        if (commaSequence == null) {
            throw new IllegalArgumentException("commaSequence == null");
        }
        mCommaSequence = commaSequence;
        return this;
    }

    public LoremIpsum paragraphSeparator(String paragraphSeparator) {
        if (paragraphSeparator == null) {
            throw new IllegalArgumentException("paragraphSeparator == null");
        }
        mParagraphSeparator = paragraphSeparator;
        return this;
    }

    @Override
    public String toString() {
        if (mWordCountMax <= 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        boolean capitalize = mCapitilize || mCapitilizeEach;
        boolean nextSentence;
        boolean newLine = true;
        int wordCount = 0;
        int totalCount;
        if (mWordCountMin == mWordCountMax) {
            totalCount = mWordCountMin;
        } else {
            totalCount = (mWordCountMin + mRandom.nextInt(mWordCountMax - mWordCountMin));
        }
        for (int i = 0; i < totalCount; i++) {
            String value = ENTRIES[mRandom.nextInt(ENTRIES.length)];
            if (!newLine) {
                result.append(mSpaceSequence);
            } else {
                newLine = false;
            }
            if (capitalize) {
                value = value.substring(0, 1).toUpperCase(Locale.US) + value.substring(1);
                capitalize = mCapitilizeEach;
            }
            result.append(value);
            nextSentence = mUseSentences && (++wordCount >= mMinSentenceLength) &&
                    (mRandom.nextInt(3) == 0);
            boolean lastWord = (i == totalCount - 1);
            if (lastWord || nextSentence) {
                if (mUseSentences) {
                    result.append(mDotSequence);
                    capitalize = mCapitilize;
                    wordCount = 0;
                }
                if (mUseParagraphs && !lastWord && mRandom.nextInt(3) == 0) {
                    result.append(mParagraphSeparator);
                    newLine = true;
                }
            } else if (mUseCommas && mRandom.nextInt(15) == 0) {
                result.append(mCommaSequence);
            }
        }

        return result.toString();
    }
}