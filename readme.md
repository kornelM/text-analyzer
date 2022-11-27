# Text search analyzer

Application made for analyzing text searches.
App splits them into one or multi words searches,
providing specific information about any of them.

## Installation

To build executable jar run:

```bash
mvn clean install
```

## Prepare working directory

Build directory with needed files like presented:

```
./app
    <copied_jar_with_dependencies_file>
    /etc
        /chart_script_template
    /input
        /<file_with_text_requests_1.txt>
        /<file_with_text_requests_2.txt

```

## Run application

```bash
java -Xmx1g -jar text-analyzer-core-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```

## Sample response

Response files are created in ./output directory.

#### JSON

```json
{
  "totalNumberOfRequests": 124,
  "mostSearchPhrases": null,
  "singleWordSearchResult": {
    "name": "SINGLE_WORD_SEARCH",
    "numberOfSearches": 29,
    "percentOfAll": 23.39,
    "averageNumberOfWords": 1.00,
    "theMostWordInSearch": 1,
    "theLeastWords": 1,
    "averageNumberOfDigits": 0.22,
    "averageNumberOfCharsPerWord": 6.04,
    "letterSearches": [
      {
        "name": "THREE_LETTER_SEARCH",
        "numberOfSearches": 4,
        "percentOfAllOneWordSearches": 13.79,
        "percentOfLetters": 83.33,
        "percentOfDigits": 16.67
      },
      {
        "name": "FOUR_LETTER_SEARCH",
        "numberOfSearches": 3,
        "percentOfAllOneWordSearches": 10.34,
        "percentOfLetters": 100.00,
        "percentOfDigits": 0.00
      },
      {
        "name": "FIVE_LETTER_SEARCH",
        "numberOfSearches": 7,
        "percentOfAllOneWordSearches": 24.14,
        "percentOfLetters": 82.86,
        "percentOfDigits": 17.14
      },
      {
        "name": "SIX_LETTER_SEARCH",
        "numberOfSearches": 7,
        "percentOfAllOneWordSearches": 24.14,
        "percentOfLetters": 100.00,
        "percentOfDigits": 0.00
      },
      {
        "name": "EIGHT_LETTER_SEARCH",
        "numberOfSearches": 3,
        "percentOfAllOneWordSearches": 10.34,
        "percentOfLetters": 100.00,
        "percentOfDigits": 0.00
      },
      {
        "name": "TEN_OR_MORE_LETTER_SEARCH",
        "numberOfSearches": 5,
        "percentOfAllOneWordSearches": 17.24,
        "percentOfLetters": 0.00,
        "percentOfDigits": 100.00
      }
    ]
  },
  "multiWordSearchResult": {
    "name": "MULTI_WORD_SEARCH",
    "numberOfSearches": 95,
    "percentOfAll": 76.61,
    "averageNumberOfWords": 4.52,
    "theMostWordInSearch": 10,
    "theLeastWords": 2,
    "averageNumberOfDigits": null,
    "averageNumberOfCharsPerWord": 4.62,
    "wordsSearches": [
      {
        "name": "TWO_WORDS_SEARCH",
        "numberOfSearches": 24,
        "percentOfAllMultiWordSearches": 25.26,
        "percentOfLettersPerSearch": 100.00,
        "percentOfDigitsPerSearch": 0.00,
        "averageNumberOfCharsPerWord": 6.26,
        "averageNumberOfWords": 2.01
      },
      {
        "name": "THREE_WORDS_SEARCH",
        "numberOfSearches": 30,
        "percentOfAllMultiWordSearches": 31.58,
        "percentOfLettersPerSearch": 77.78,
        "percentOfDigitsPerSearch": 22.22,
        "averageNumberOfCharsPerWord": 4.18,
        "averageNumberOfWords": 3.00
      },
      {
        "name": "FOUR_WORDS_SEARCH",
        "numberOfSearches": 13,
        "percentOfAllMultiWordSearches": 13.68,
        "percentOfLettersPerSearch": 100.00,
        "percentOfDigitsPerSearch": 0.00,
        "averageNumberOfCharsPerWord": 4.60,
        "averageNumberOfWords": 4.01
      },
      {
        "name": "FIVE_WORDS_SEARCH",
        "numberOfSearches": 4,
        "percentOfAllMultiWordSearches": 4.21,
        "percentOfLettersPerSearch": 100.00,
        "percentOfDigitsPerSearch": 0.00,
        "averageNumberOfCharsPerWord": 3.71,
        "averageNumberOfWords": 5.00
      },
      {
        "name": "SIX_WORDS_SEARCH",
        "numberOfSearches": 3,
        "percentOfAllMultiWordSearches": 3.16,
        "percentOfLettersPerSearch": 100.00,
        "percentOfDigitsPerSearch": 0.00,
        "averageNumberOfCharsPerWord": 3.67,
        "averageNumberOfWords": 6.00
      },
      {
        "name": "SEVEN_WORDS_SEARCH",
        "numberOfSearches": 3,
        "percentOfAllMultiWordSearches": 3.16,
        "percentOfLettersPerSearch": 100.00,
        "percentOfDigitsPerSearch": 0.00,
        "averageNumberOfCharsPerWord": 3.86,
        "averageNumberOfWords": 7.01
      },
      {
        "name": "EIGHT_WORDS_SEARCH",
        "numberOfSearches": 3,
        "percentOfAllMultiWordSearches": 3.16,
        "percentOfLettersPerSearch": 100.00,
        "percentOfDigitsPerSearch": 0.00,
        "averageNumberOfCharsPerWord": 4.01,
        "averageNumberOfWords": 8.01
      },
      {
        "name": "NINE_WORDS_SEARCH",
        "numberOfSearches": 3,
        "percentOfAllMultiWordSearches": 3.16,
        "percentOfLettersPerSearch": 100.00,
        "percentOfDigitsPerSearch": 0.00,
        "averageNumberOfCharsPerWord": 4.01,
        "averageNumberOfWords": 9.00
      },
      {
        "name": "MORE_THAN_NINE_WORD_SEARCH",
        "numberOfSearches": 12,
        "percentOfAllMultiWordSearches": 12.63,
        "percentOfLettersPerSearch": 71.23,
        "percentOfDigitsPerSearch": 28.77,
        "averageNumberOfCharsPerWord": 3.50,
        "averageNumberOfWords": 10.76
      }
    ],
    "potentialSqlInjections": [
      "select * from",
      "drop table users",
      "1 = 1",
      "select *",
      "; drop",
      "drop table",
      "select * from users *",
      "drop table users --",
      "select * from users",
      "drop table * --"
    ]
  },
  "digitSearchResult": null
}
```

#### HTML

```html

<html>
<head>
    <meta content="content-type" charset="UTF-8">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
    </script>
</head>
<body>
<div>
    <h1>
        Statystyki zapytań:
    </h1>
</div>
<div>
    <h2 style="color:blue">
        JEDNOSŁOWNYCH
    </h2>
    <div>
        <h3>
        </h3>
        <p>
            Single-word search: SINGLE_WORD_SEARCH
        </p>
        <p>
            Number of searches: 29
        </p>
        <p>
            Percent of all searches: 23.39%
        </p>
        <p>
            Average number of words: 1.00
        </p>
        <p>
            The most words in search: 1
        </p>
        <p>
            The least words in search: 1
        </p>
        <p>
            Average number of chars per word: 6.04
        </p>
    </div>
</div>
<h3>
    THREE_LETTER_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 4
    </p>
    <p>
        Percent of all single-word searches: 13.79%
    </p>
    <p>
        Percent of letters in searches: 83.33%
    </p>
    <p>
        Percent of digits in searches: 16.67%
    </p>
</h3>
<h3>
    FOUR_LETTER_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 3
    </p>
    <p>
        Percent of all single-word searches: 10.34%
    </p>
    <p>
        Percent of letters in searches: 100.00%
    </p>
    <p>
        Percent of digits in searches: 0.00%
    </p>
</h3>
<h3>
    FIVE_LETTER_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 7
    </p>
    <p>
        Percent of all single-word searches: 24.14%
    </p>
    <p>
        Percent of letters in searches: 82.86%
    </p>
    <p>
        Percent of digits in searches: 17.14%
    </p>
</h3>
<h3>
    SIX_LETTER_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 7
    </p>
    <p>
        Percent of all single-word searches: 24.14%
    </p>
    <p>
        Percent of letters in searches: 100.00%
    </p>
    <p>
        Percent of digits in searches: 0.00%
    </p>
</h3>
<h3>
    EIGHT_LETTER_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 3
    </p>
    <p>
        Percent of all single-word searches: 10.34%
    </p>
    <p>
        Percent of letters in searches: 100.00%
    </p>
    <p>
        Percent of digits in searches: 0.00%
    </p>
</h3>
<h3>
    TEN_OR_MORE_LETTER_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 5
    </p>
    <p>
        Percent of all single-word searches: 17.24%
    </p>
    <p>
        Percent of letters in searches: 0.00%
    </p>
    <p>
        Percent of digits in searches: 100.00%
    </p>
</h3>
<div>
    <h2 style="color:blue">
        WIELOSŁOWNYCH
    </h2>
    <div>
        <h3>
        </h3>
        <p>
            Multi-word search: MULTI_WORD_SEARCH
        </p>
        <p>
            Number of searches: 95
        </p>
        <p>
            Percent of all searches: 76.61%
        </p>
        <p>
            Average number of words: 4.52
        </p>
        <p>
            The most words in search: 10
        </p>
        <p>
            The least words in search: 2
        </p>
        <p>
            Average number of chars per word: 4.62
        </p>
    </div>
</div>
<h3>
    TWO_WORDS_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 24
    </p>
    <p>
        Percent of all multi-word searches: 25.26%
    </p>
    <p>
        Percent of letters in searches: 100.00%
    </p>
    <p>
        Percent of digits in searches: 0.00%
    </p>
    <p>
        Average number of chars per word: 6.26
    </p>
    <p>
        Average number of words: 2.01
    </p>
</h3>
<h3>
    THREE_WORDS_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 30
    </p>
    <p>
        Percent of all multi-word searches: 31.58%
    </p>
    <p>
        Percent of letters in searches: 77.78%
    </p>
    <p>
        Percent of digits in searches: 22.22%
    </p>
    <p>
        Average number of chars per word: 4.18
    </p>
    <p>
        Average number of words: 3.00
    </p>
</h3>
<h3>
    FOUR_WORDS_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 13
    </p>
    <p>
        Percent of all multi-word searches: 13.68%
    </p>
    <p>
        Percent of letters in searches: 100.00%
    </p>
    <p>
        Percent of digits in searches: 0.00%
    </p>
    <p>
        Average number of chars per word: 4.60
    </p>
    <p>
        Average number of words: 4.01
    </p>
</h3>
<h3>
    FIVE_WORDS_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 4
    </p>
    <p>
        Percent of all multi-word searches: 4.21%
    </p>
    <p>
        Percent of letters in searches: 100.00%
    </p>
    <p>
        Percent of digits in searches: 0.00%
    </p>
    <p>
        Average number of chars per word: 3.71
    </p>
    <p>
        Average number of words: 5.00
    </p>
</h3>
<h3>
    SIX_WORDS_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 3
    </p>
    <p>
        Percent of all multi-word searches: 3.16%
    </p>
    <p>
        Percent of letters in searches: 100.00%
    </p>
    <p>
        Percent of digits in searches: 0.00%
    </p>
    <p>
        Average number of chars per word: 3.67
    </p>
    <p>
        Average number of words: 6.00
    </p>
</h3>
<h3>
    SEVEN_WORDS_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 3
    </p>
    <p>
        Percent of all multi-word searches: 3.16%
    </p>
    <p>
        Percent of letters in searches: 100.00%
    </p>
    <p>
        Percent of digits in searches: 0.00%
    </p>
    <p>
        Average number of chars per word: 3.86
    </p>
    <p>
        Average number of words: 7.01
    </p>
</h3>
<h3>
    EIGHT_WORDS_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 3
    </p>
    <p>
        Percent of all multi-word searches: 3.16%
    </p>
    <p>
        Percent of letters in searches: 100.00%
    </p>
    <p>
        Percent of digits in searches: 0.00%
    </p>
    <p>
        Average number of chars per word: 4.01
    </p>
    <p>
        Average number of words: 8.01
    </p>
</h3>
<h3>
    NINE_WORDS_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 3
    </p>
    <p>
        Percent of all multi-word searches: 3.16%
    </p>
    <p>
        Percent of letters in searches: 100.00%
    </p>
    <p>
        Percent of digits in searches: 0.00%
    </p>
    <p>
        Average number of chars per word: 4.01
    </p>
    <p>
        Average number of words: 9.00
    </p>
</h3>
<h3>
    MORE_THAN_NINE_WORD_SEARCH
    <h3>
    </h3>
    <p>
        Number of searches: 12
    </p>
    <p>
        Percent of all multi-word searches: 12.63%
    </p>
    <p>
        Percent of letters in searches: 71.23%
    </p>
    <p>
        Percent of digits in searches: 28.77%
    </p>
    <p>
        Average number of chars per word: 3.50
    </p>
    <p>
        Average number of words: 10.76
    </p>
</h3>
<canvas id="SINGLE_AND_MULTI_SEARCHES" style="width:100%;max-width:1200px">
</canvas>
<script>
            var xValues = ["SINGLE_WORD_SEARCH","MULTI_WORD_SEARCH"];
            var yValues = ["23.39","76.61"];
            var barColors = ["#0c4004","#269615"];
            
            new Chart("SINGLE_AND_MULTI_SEARCHES", {
              type: "pie",
              data: {
                labels: xValues,
                datasets: [{
                  backgroundColor: barColors,
                  data: yValues
                }]
              },
              options: {
                title: {
                  display: true,
                  text: "Wykres stosunku zapytań jednosłownych do wielosłownych"
                }
              }
            });
        
</script>
<canvas id="LETTERS_SEARCHES" style="width:100%;max-width:1200px">
</canvas>
<script>
            var xValues = ["THREE_LETTER_SEARCH","FOUR_LETTER_SEARCH","FIVE_LETTER_SEARCH","SIX_LETTER_SEARCH","EIGHT_LETTER_SEARCH","TEN_OR_MORE_LETTER_SEARCH"];
            var yValues = ["13.79","10.34","24.14","24.14","10.34","17.24"];
            var barColors = ["#0c4004","#0217f7","#071070","#5e5c5c","#e30b0b","#8305f2"];
            
            new Chart("LETTERS_SEARCHES", {
              type: "pie",
              data: {
                labels: xValues,
                datasets: [{
                  backgroundColor: barColors,
                  data: yValues
                }]
              },
              options: {
                title: {
                  display: true,
                  text: "Wykres zapytań jednosłownych"
                }
              }
            });
        
</script>
<canvas id="WORDS_SEARCHES" style="width:100%;max-width:1200px">
</canvas>
<script>
            var xValues = ["TWO_WORDS_SEARCH","THREE_WORDS_SEARCH","FOUR_WORDS_SEARCH","FIVE_WORDS_SEARCH","SIX_WORDS_SEARCH","SEVEN_WORDS_SEARCH","EIGHT_WORDS_SEARCH","NINE_WORDS_SEARCH","MORE_THAN_NINE_WORD_SEARCH"];
            var yValues = ["25.26","31.58","13.68","4.21","3.16","3.16","3.16","3.16","12.63"];
            var barColors = ["#0c4004","#0217f7","#ed7002","#02f779","#5e5c5c","#f5d907","#e30b0b","#592d1d","#071070"];
            
            new Chart("WORDS_SEARCHES", {
              type: "pie",
              data: {
                labels: xValues,
                datasets: [{
                  backgroundColor: barColors,
                  data: yValues
                }]
              },
              options: {
                title: {
                  display: true,
                  text: "Wykres zapytań wielosłownych"
                }
              }
            });
        
</script>
</body>
</html>

```

## License

[MIT](https://choosealicense.com/licenses/mit/)
