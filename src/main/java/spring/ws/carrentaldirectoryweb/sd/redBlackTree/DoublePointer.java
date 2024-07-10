package spring.ws.carrentaldirectoryweb.sd.redBlackTree;

import spring.ws.carrentaldirectoryweb.sd.redBlackTree.info.ListInfo;

public class DoublePointer {
    public DoublePointer previous;
    public DoublePointer next;
    public double data;


    public static DoublePointer SearchByValue(ListInfo list, double value){
        DoublePointer point = new DoublePointer();
        DoublePointer search = new DoublePointer();

        if(list.head != null){
            point = list.head;
        }
        else {
            System.out.println("Отсутствуют элементы в списке");
        }

        while(point.next != null | search.data != value){
            if(point.data == value){
                search = point;
            }
            if(point.next == null) {
                break;
            }
            else point = point.next;
        }

        return search;
    }

    public static ListInfo AddList(ListInfo list, double value) {
        DoublePointer item = new DoublePointer();
        DoublePointer point;
        item.data = value;  

        if (list == null || list.head == null) {
            list = new ListInfo();
            DoublePointer head = new DoublePointer();
            head.data = value;
            list.head = head;
            list.tail = head;

        } else {
            point = list.head;

            if (point.data < item.data) {
                item.next = point;
                point.previous = item;
                list.head = item;
            } else {
                while (point.next != null && item.data <= point.next.data) {
                    point = point.next;
                }
                if (point.next != null) {
                    point.next.previous = item;
                }

                item.next = point.next;
                point.next = item;
                item.previous = point;
            }

            list.tail = list.head;
            while (list.tail.next != null) {
                list.tail = list.tail.next;
            }
        }

        return list;
    }

    public static void deleteItemByValue(ListInfo list, double value) {
        DoublePointer current = list.head;

        while (current != null) {
            if (current.data == value) {
                if (current == list.head) {
                    list.head = current.next;
                    if (list.head != null) {
                        list.head.previous = null;
                    }
                    if (current == list.tail) {
                        list.tail = null;
                    }
                } else if (current == list.tail) {
                    list.tail = current.previous;
                    list.tail.next = null;
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                return;
            }
            current = current.next;
        }
    }

    public static String ListPrint(ListInfo list) {
        StringBuilder strings = new StringBuilder("|");
        if (list == null){
            System.out.println("Список не инициализирован");
        }
        if (list.head == null) {
            System.out.println("Отсутствуют элементы в списке");
            return "";
        }

        DoublePointer current = list.head;

        while (current != null) {
            strings.append(Integer.toString((int) current.data)).append("|");
            current = current.next;
        }
        return strings.toString();
    }

    public DoublePointer() {
        previous = null;
        next = null;
    }
}
