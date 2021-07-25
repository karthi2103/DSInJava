package JLinkedList;

import com.google.common.collect.ImmutableList;
import implementation.JLinkedList.SinglyLinkedList.SinglyLinkedList;
import implementation.JLinkedList.doublyLinkedList.DoublyLinkedList;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;

public class DoublyLinkedListTest {
    private static final String STAR = "STAR";
    private static final String WARS = "WARS";
    private static final String TREK = "TREK";
    private static final String GATE = "GATE";
    private static final String TO_STRING = "[STAR, WARS, TREK, GATE]";
    private DoublyLinkedList<String> list;

    @BeforeMethod
    public void setup(ITestResult result) {
        System.out.println();
        System.out.println("STARTING TESTS FOR: "+ result.getMethod().getMethodName());
        System.out.println("===============================================");
        System.out.println();
        list = new DoublyLinkedList<>();
    }

    @AfterMethod
    public void teardown() {
        System.out.println();
        System.out.println("===============================================");
        System.out.println();
    }

    @Test()
    public void testEmptyList() {
        list.print();
        assert list.isEmpty();
        assert list.getSize() == 0;
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testDeleteFirstOfEmpty() {
        list.print();
        list.deleteFirst();
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testDeleteLastOfEmpty() {
        list.print();
        list.deleteLast();
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testPeekFirstOfEmpty() {
        list.print();
        list.peekFirst();
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testPeekLastOfEmpty() {
        list.print();
        list.peekLast();
    }

    @Test
    public void testCreateListWithIterator() {
        ImmutableList<String> i = ImmutableList.of(STAR, WARS, TREK, GATE);
        SinglyLinkedList<String> linkedList = new SinglyLinkedList<>(i.stream().iterator());
        assert linkedList.getSize() == 4;
        linkedList.print();
    }

    @Test
    public void testCreateListWithCollection() {
        ImmutableList<String> i = ImmutableList.of(STAR, WARS, TREK, GATE);
        SinglyLinkedList<String> linkedList = new SinglyLinkedList<>(i);
        assert linkedList.getSize() == 4;
        linkedList.print();
    }

    @Test
    public void testCreateListWithValue() {
        SinglyLinkedList<String> linkedList = new SinglyLinkedList<>(STAR);
        assert linkedList.getSize() == 1;
        linkedList.print();
    }

    @Test
    public void testPrependToList() {
        list.prepend(STAR);
        list.print();
        assert list.getSize() == 1;
        assert list.peekFirst().equals(STAR);
        list.prepend(WARS);
        list.print();
        assert list.getSize() == 2;
        assert list.peekFirst().equals(WARS);
        assert list.peekLast().equals(STAR);
    }

    @Test
    public void testAppendToList() {
        list.append(STAR);
        list.print();
        assert list.getSize() == 1;
        assert list.peekFirst().equals(STAR);
        list.append(WARS);
        list.print();
        assert list.getSize() == 2;
        assert list.peekFirst().equals(STAR);
        assert list.peekLast().equals(WARS);
    }

    @Test
    public void testAddAtIndex() {
        list.addAtIndex(0, STAR);
        list.print();
        assert list.getSize() == 1;
        assert list.peekFirst().equals(STAR);
        list.addAtIndex(1, WARS);
        list.print();
        assert list.getSize() == 2;
        assert list.peekFirst().equals(STAR);
        assert list.getAtIndex(1).equals(WARS);
        list.addAtIndex(1, TREK);
        list.print();
        assert list.getSize() == 3;
        assert list.peekFirst().equals(STAR);
        assert list.getAtIndex(1).equals(TREK);
        assert list.getAtIndex(2).equals(WARS);
        list.addAtIndex(2, GATE);
        list.print();
        assert list.getSize() == 4;
        assert list.peekFirst().equals(STAR);
        assert list.getAtIndex(1).equals(TREK);
        assert list.getAtIndex(2).equals(GATE);
        assert list.getAtIndex(3).equals(WARS);
    }

    @Test
    public void testDeleteFirst() {
        list.addAtIndex(0, STAR);
        list.print();
        String first = list.deleteFirst();
        assert STAR.equals(first);
        assert list.isEmpty();
        assert list.getSize() == 0;
    }

    @Test
    public void testDeleteLast() {
        list.addAtIndex(0, STAR);
        list.print();
        list.addAtIndex(1, WARS);
        list.print();
        list.addAtIndex(2, TREK);
        list.print();
        list.addAtIndex(3, GATE);
        list.print();
        String last = list.deleteLast();
        list.print();
        assert last.equals(GATE);
        last = list.deleteLast();
        list.print();
        assert last.equals(TREK);
        last = list.deleteLast();
        list.print();
        assert last.equals(WARS);
        last = list.deleteLast();
        list.print();
        assert last.equals(STAR);
        assert list.getSize() == 0;
        assert list.isEmpty();
    }

    @Test
    public void testDeletion() {
        list.add(STAR);
        list.print();
        list.add(WARS);
        list.print();
        list.add(TREK);
        list.print();
        list.add(GATE);
        list.print();
        assert list.getSize() == 4;
        list.delete(STAR);
        list.print();
        assert list.getSize() == 3;
        assert list.peekFirst().equals(WARS);
        list.delete(GATE);
        list.print();
        assert list.peekLast().equals(TREK);
        list.delete(TREK);
        list.print();
        assert list.contains(WARS);
        list.delete(WARS);
        list.print();
        assert list.getSize() == 0;
        assert list.isEmpty();
    }

    @Test
    public void testDeleteAtIndex() {
        list.add(STAR);
        list.print();
        list.add(WARS);
        list.print();
        list.add(TREK);
        list.print();
        list.add(GATE);
        list.print();
        list.deleteAtIndex(1);
        list.print();
        assert list.getAtIndex(1).equals(TREK);
        list.deleteAtIndex(0);
        list.print();
        assert list.peekFirst().equals(TREK);
        assert list.getSize() == 2;
        list.deleteAtIndex(1);
        list.print();
        assert list.peekLast().equals(TREK);
        assert list.peekFirst().equals(TREK);
    }

    @Test
    public void testToString() {
        list.add(STAR);
        list.add(WARS);
        list.add(TREK);
        list.add(GATE);
        assert list.toString().equals(TO_STRING);
        list.print();
    }

    @Test
    public void testListIterator() {
        ImmutableList<String> i = ImmutableList.of(STAR, WARS, GATE, TREK);
        list.addAll(i);
        list.print();
        Iterator<String> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            assert iterator.next().equals(i.get(index));
            index ++;
        }
    }
}
