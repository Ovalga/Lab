package org.example.functions;

import exceptions.InterpolationException;

import java.util.Arrays;
import java.util.Objects;


public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    static class Node {

        public double x;
        public double y;
        public Node next;
        public Node prev;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            String StrX = String.valueOf(x);
            String StrY = String.valueOf(y);

            return ("(" + StrX + ";" + StrY + ")" + ", где " + StrX + " и " + StrY + " – абсцисса и ордината точки соответственно.");
        }

        @Override
        public Object clone() {
            Node cloneNode = new Node(x, y);
            cloneNode.prev = this.prev;
            cloneNode.next = this.next;
            return cloneNode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Double.compare(x, node.x) == 0 && Double.compare(y, node.y) == 0 && Objects.equals(next, node.next) && Objects.equals(prev, node.prev);
        }

        @Override
        public int hashCode() {
            int result = 31 * Double.hashCode(x);
            result = 31 * result + Double.hashCode(y);
            return result;
        }
    }

    private int count;
    private Node head;

    private void addNode(double x, double y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            Node last = head.prev;
            last.next = newNode;
            head.prev = newNode;
            newNode.prev = last;
            newNode.next = head;
        }
        count++;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; ++i) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {

            double step = (xFrom - xTo) / count - 1;
            for (int i = 0; i < count; ++i) {
                addNode((xTo + (i * step)), source.apply(xTo + (i * step)));
            }

        } else if (xTo > xFrom) {
            double step = (xTo - xFrom) / count;
            for (int i = 0; i < count; ++i) {
                addNode((xFrom + (i * step)), source.apply(xFrom + (i * step)));
            }

        } else {
            for (int i = 0; i < count; ++i) {
                addNode(xFrom, source.apply(xFrom));
            }

        }

    }

    public double[][] arrayLink() {
        double[] xArray = new double[count];
        double[] yArray = new double[count];
        int i = 0;
        for (Node temp = head; temp != head.prev; temp = temp.next) {
            xArray[i] = temp.x;
            yArray[i] = temp.y;
            i++;
        }
        xArray[count - 1] = head.prev.x;
        yArray[count - 1] = head.prev.y;
        return new double[][]{xArray, yArray};
    }

    public int getCount() {
        return count;
    }

    public double leftBound() {
        return head.x;
    }

    public double rightBound() {
        return head.prev.x;
    }

    Node getNode(int index) {

        if (index == 0) {
            return head;
        }
        Node temp = head;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;

        }
        return temp.prev;
    }

    public double getX(int index) {
        Node temp = getNode(index);
        return temp.x;
    }

    public double getY(int index) {
        Node temp = getNode(index);
        return temp.y;
    }

    public void setY(int index, double value) {

        Node temp = getNode(index);
        temp.y = value;

    }

    public int indexOfX(double x) {
        int index = 0;
        int i = 0;
        Node temp = head;
        while ((temp.x != x) && (temp != head.prev)) {
            temp = temp.next;
            ++index;
        }

        if (temp == head.prev) {
            if (temp.x == x) {
                return index;
            } else return -1;
        } else return index;


    }

    public int indexOfY(double y) {
        int index = 0;
        int i = 0;
        Node temp = head;
        while ((temp.y != y) && (temp != head.prev)) {
            temp = temp.next;
            ++index;
        }

        if (temp == head.prev) {
            if (temp.y == y) {
                return index;
            } else return -1;
        } else return index;


    }


    protected int floorIndexOfX(double x) {
        int index = 0;
        if (head.x > x) {
            return 0;
        } else if (head.prev.x < x) {
            return count;
        } else {
            for (Node temp = head; ; temp = temp.next) {
                if (temp.x == x) {
                    return index;
                } else if (temp.x > x) {
                    return index - 1;
                }
                index++;

            }
        }


    }

    protected double interpolate(double x, int floorIndex) {
        if (x < floorIndex && x > floorIndex - 1) {
            if (head.next == head) {
                return head.y;
            } else {
                double leftX = getX(floorIndex - 1);
                double rightX = getX(floorIndex);
                double leftY = getY(floorIndex - 1);
                double rightY = getY(floorIndex);
                return interpolate(x, leftX, rightX, leftY, rightY);
            }
        } else throw new InterpolationException("X не лежит в интервале");

    }

    protected double extrapolateLeft(double x) {
        if (head.next == head) {
            return head.y;
        } else return (head.y + (((head.prev.y - head.y) / (head.prev.x - head.x)) * (x - head.x)));
    }

    protected double extrapolateRight(double x) {
        if (head.next == head) {
            return head.y;
        } else
            return (head.prev.prev.y + (((head.prev.y - head.prev.prev.y) / (head.prev.x - head.prev.prev.x)) * (x - head.prev.prev.x)));
    }

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        if (head.next == head) {
            return head.y;
        } else return (leftY + (((rightY - leftY) / (rightX - leftX)) * (x - leftX)));
    }

    public double apply(double x) {
        double result;
        if (x < head.x) {
            result = extrapolateLeft(x);
        } else if (x > head.prev.x) {
            result = extrapolateRight(x);
        } else {
            if (indexOfX(x) != -1) {
                result = getY(indexOfX(x));
            } else {
                result = interpolate(x, floorIndexOfX(x));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String xAndYStr = "";
        for (Node temp = head; temp != head.prev; temp = temp.next) {
            String StrX = String.valueOf(temp.x);
            String StrY = String.valueOf(temp.y);
            xAndYStr += "(" + StrX + ";" + StrY + ")" + " ";
        }
        xAndYStr += "(" + String.valueOf(head.prev.x) + ";" + String.valueOf(head.prev.y) + ")" + " ";
        return xAndYStr;
    }


    @Override
    public int hashCode() {
        int hashResult = 19;
        for (Node temp = head; temp != head.prev; temp = temp.next) {

            hashResult = hashResult * 31 + temp.hashCode();
        }
        hashResult = hashResult * 31 + head.prev.hashCode();
        return hashResult;
    }

    @Override
    public boolean equals(Object o) {
        LinkedListTabulatedFunction temp = (LinkedListTabulatedFunction) o;
        double[][] arr1 = temp.arrayLink();
        double[][] arr2 = this.arrayLink();


        if (o.getClass() == this.getClass() && Arrays.deepEquals(arr1, arr2)) {
            return true;
        } else return false;
    }

    @Override
    public Object clone() {
        double[] xArray = new double[count];
        double[] yArray = new double[count];
        int i = 0;
        for (Node temp = head; temp != head.prev; temp = temp.next) {
            xArray[i] = temp.x;
            yArray[i] = temp.y;
            i++;
        }
        xArray[count - 1] = head.prev.x;
        yArray[count - 1] = head.prev.y;
        return new LinkedListTabulatedFunction(xArray, yArray);
    }
}
