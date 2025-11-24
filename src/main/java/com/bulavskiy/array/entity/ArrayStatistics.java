package com.bulavskiy.array.entity;

public class ArrayStatistics {
  private final int sum;
  private final double average;
  private final int min;
  private final int max;

  public ArrayStatistics(int sum, double average, int min, int max) {
    this.sum = sum;
    this.average = average;
    this.min = min;
    this.max = max;
  }

  public int getSum() {
    return sum;
  }

  public double getAverage() {
    return average;
  }

  public int getMin() {
    return min;
  }

  public int getMax() {
    return max;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ArrayStatistics{");
    sb.append("sum=").append(sum);
    sb.append(", average=").append(average);
    sb.append(", min=").append(min);
    sb.append(", max=").append(max);
    sb.append('}');
    return sb.toString();
  }
}
