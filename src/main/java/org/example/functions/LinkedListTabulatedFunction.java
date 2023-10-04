package org.example.functions;

import java.util.Arrays;


public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    class Node {

        public double x;
        public double y;
        public Node next;
        public Node prev;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
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

    int getCount() {
        return count;
    }

    double leftBound() {
        return head.x;
    }

    double rightBound() {
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
        return temp;
    }

    double getX(int index) {
        if (index == 0) {
            return head.x;
        } else {
            Node temp = head;
            for (int i = 1; i <= index; i++) {
                temp = temp.next;

            }
            return temp.x;
        }
    }

    double getY(int index) {
        if (index == 0) {
            return head.y;
        } else {
            Node temp = head;
            for (int i = 1; i <= index; i++) {
                temp = temp.next;

            }
            return temp.y;
        }
    }

    void setY(int index, double value) {

        if (index == 0) {
            head.y = value;
        } else {
            Node temp = head;
            for (int i = 1; i <= index; i++) {
                temp = temp.next;

            }
            temp.y = value;
        }
    }

    int indexOfX(double x) {
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

    int indexOfY(double y) {
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
        if (head.next == head) {
            return head.y;
        } else {
            double leftX = getX(floorIndex - 1);
            double rightX = getX(floorIndex);
            double leftY = getY(floorIndex - 1);
            double rightY = getY(floorIndex);
            return interpolate(x, leftX, rightX, leftY, rightY);
        }

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

    protected double apply(double x) {
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
}
