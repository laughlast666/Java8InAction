package lambdasinaction.chap5;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class C5BuildingStreams {
    public static final String CHAP_5_DATA_TXT = "lambdasinaction/chap5/data.txt";

    public static void main(String... args) throws Exception {

        System.out.println("---- Stream.of ");
        Stream<String> stringStream = Stream.of("Java 8", "Lambda", "In", "Action");
        stringStream.map(String::toUpperCase).forEach(System.out::println);

        System.out.println("---- Stream.empty ");
        Stream<String> emptyStream = Stream.empty();

        System.out.println("---- Array.stream");
        int[] numbers = {1, 3, 5, 7};
        System.out.println(Arrays.stream(numbers).sum());

        System.out.println("---- Stream.iterate");
        Stream.iterate(0, n -> n + 2).limit(5).forEach(System.out::println);

        System.out.println("---- fibonnaci via Stream.iterate");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(10)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(10).map(t -> t[0])
                .forEach(System.out::println);
        System.out.println("---- ramdom via Stream.generate");
        Stream.generate(Math::random).limit(10).forEach(System.out::println);

        System.out.println("---- one number via Stream.generate");
        Stream.generate(() -> 1).limit(5).forEach(System.out::println);
        IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        }).limit(5).forEach(System.out::println);

        System.out.println("---- fibonnaci via IntSupplier");
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 0;

            @Override
            public int getAsInt() {
                //the 1st time and the 1st number: 0
                if (0 == this.current) {
                    this.current = 1;
                } else {
                    int nextValue = this.previous + this.current;
                    this.previous = this.current;
                    this.current = nextValue;
                }
                return this.previous;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);

        System.out.println("---- Nio stream etc ");

        Files.lines(Paths.get(CHAP_5_DATA_TXT), Charset.defaultCharset())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct().forEach(System.out::println);

        long wordsCount = Files.lines(Paths.get(CHAP_5_DATA_TXT), Charset.defaultCharset())
                .flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        System.out.println("There are " + wordsCount + " unique words in " + CHAP_5_DATA_TXT);

    }
}
