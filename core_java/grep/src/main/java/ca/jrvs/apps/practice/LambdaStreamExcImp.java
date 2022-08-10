package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc{

  @Override
  public Stream<String> createStrStream(String... strings) {
    return Arrays.stream(strings);
  }

  @Override
  public Stream<String> toUpperCase(String... strings) {
    return createStrStream(strings).map(String::toUpperCase);
  }

  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter(value -> !value.contains(pattern));
  }

  @Override
  public IntStream createIntStream(int[] arr) {
    return Arrays.stream(arr);
  }

  @Override
  public <E> List<E> toList(Stream<E> stream) {
    return stream.collect(Collectors.toList());
  }

  @Override
  public List<Integer> toList(IntStream intStream) {
    return toList(intStream);
  }

  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.range(start, end);
  }

  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    return intStream.asDoubleStream().map(Math::sqrt);
  }

  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(value -> value%2 == 0);
  }

  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    return value -> System.out.println(prefix + value + suffix);
  }

  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
    createStrStream(messages).forEach(printer);
  }

  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    getOdd(intStream).mapToObj(String::valueOf).forEach(printer);
  }

  public static void main(String[] args) {
    String[] arr = {"Sergio","Georges","HIa"};
    int[] num = {1,2,3,4,5};
    LambdaStreamExcImp le = new LambdaStreamExcImp();

//    Stream<String> s = le.createStrStream(arr);
//    s = le.filter(s,"gi");
//    List<String> l = le.toList(s);
//    l.stream().forEach(System.out::println);

    IntStream i = le.createIntStream(num);
    Consumer<String> printer = null;
    le.printMessages(arr,le.getLambdaPrinter("msg:","!"));
    le.printOdd(i,le.getLambdaPrinter("num:","!"));
  }
}
