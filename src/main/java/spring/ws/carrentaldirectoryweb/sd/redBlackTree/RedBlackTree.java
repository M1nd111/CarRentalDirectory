package spring.ws.carrentaldirectoryweb.sd.redBlackTree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import spring.ws.carrentaldirectoryweb.core.Hellper.DebugMessage;
import spring.ws.carrentaldirectoryweb.core.Hellper.SearchMessage;
import spring.ws.carrentaldirectoryweb.sd.list.info.ListInfo;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.entity.NilNode;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.entity.Node;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsReadDto;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.info.Info;
import spring.ws.carrentaldirectoryweb.sd.hashTable.hash.TableEntity.DynamicTableStatus01;

import java.time.LocalDate;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RedBlackTree extends Info {
    private Integer hashTableSize;
    public  void printStruct(Node node, int level) {

        if (node != null) {
            printStruct(node.right, level + 1);
            for (int i = 0; i < level; i++) {

                System.out.print("--------------");
                DebugMessage.message += "--------------";
            }
            if (!node.color) {
                System.out.print("\033[31m" + node.data + " -> " + "\033[0m" );
                DebugMessage.message += "RED " + node.data + " -> ";
                node.hashTable.printTable(level);
            } else {
                System.out.print(node.data + " -> ");
                DebugMessage.message += node.data + " -> ";
                node.hashTable.printTable(level);
            }
            printStruct(node.left, level + 1);
        }
    }
    public void printTree(Node node, int level) {
        if (node != null) {
            printTree(node.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("          ");
            }
            if (!node.color) {
                System.out.println("\033[31m" + node.data + " -> " + node.hashTable.toString() + "\033[0m");
            } else {
                System.out.println(node.data + " -> " + node.hashTable.toString());            }
            printTree(node.left, level + 1);
        }
    }

    public  void printLinesTree(Node node, int level) {
        if (node != null) {
            printLinesTree(node.right, level + 1);
            System.out.print(node.data + " -> \t");
            node.hashTable.printTable();
            printLinesTree(node.left, level + 1);
        }
    }



    public void addAllRecords(Node node, int level) {
        if (node != null) {
            addAllRecords(node.right, level + 1);
            node.hashTable.addToDb();
            addAllRecords(node.left, level + 1);
        }
    }

    public  void printLinesTreeWithPeriodForDate(Node node, String markName, LocalDate first, LocalDate second) {

        int level = 0;
        if (node != null) {
            printLinesTreeWithPeriodForDate(node.right, markName, first, second);

            LocalDate localDate = node.data;
            if( ((localDate.isAfter(first) && localDate.isBefore(second))
                    || localDate.isEqual(first) || localDate.isEqual(second))
                    && node.hashTable.findMarkName(markName) ) {
                System.out.println(node.hashTable.returnByMarkName(markName));
                SearchMessage.message += node.hashTable.returnByMarkName(markName) + "\n";
            }

            printLinesTreeWithPeriodForDate(node.left, markName, first, second);
        }
    }

    public void ResetTree(Node node){
        if (node != null) {
            ResetTree(node.left);
            ResetTree(node.right);
            node.left = null;
            node.right = null;
            node.parent = null;
        }
        root = null;
    }

    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node leftChild = node.left;

        node.left = leftChild.right;
        if(leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.right = node;
        node.parent = leftChild;

        replaceParentsChild(parent, node, leftChild);
    }

    private  void rotateLeft(Node node) {
        Node parent = node.parent;
        Node rightChild = node.right;

        node.right = rightChild.left;
        if(rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.left = node;
        node.parent = rightChild;

        replaceParentsChild(parent, node, rightChild);

    }

    private void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else if (parent.right == oldChild) {
            parent.right = newChild;
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
    }

    public Node searchNode(LocalDate key) {
        Node node = root;
        while(node != null) {
            if(node.data.equals(key)) {
                return node;
            } else if(node.data.isAfter(key)) {
                node = node.left;
            } else if(key.isAfter(node.data)) {
                node = node.right;
            }
        }
        return null;
    }

    public void insertNode(RecordsReadDto readDto) {
        Node node = root;
        LocalDate key = readDto.getFirst_date();
        Node parent = null;
        boolean dublicate = false;

        while(node != null){
            parent = node;
            if(node.data.isAfter(key)) {
                node = node.left;
            } else if(key.isAfter(node.data)) {
                node = node.right;
            } else {
                node.hashTable.put(readDto.getMarkName(),
                        DynamicTableStatus01.builder().id(readDto.getId()).line(readDto.toString()).build());
                System.out.println("already contains a node with key " + key);
                dublicate = true;
                break;
            }
        }

        if(!dublicate) {
            Node newNode = new Node(readDto, hashTableSize);
            newNode.color = false;

            if (parent == null) {
                root = newNode;
            } else if (parent.data.isAfter(key)) {
                parent.left = newNode;
                newNode.parent = parent;
            } else {
                parent.right = newNode;
                newNode.parent = parent;
            }

            fixRedBlackTiesAfterInsert(newNode);
        }
    }
    private  Node getUncle(Node parent) {
        Node grandparent = parent.parent;
        if (grandparent.left == parent) {
            return grandparent.right;
        } else if (grandparent.right == parent) {
            return grandparent.left;
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }

    private  void fixRedBlackTiesAfterInsert(Node node){
        Node parent = node.parent;

        //case 1
        if(parent == null){
            node.color = true;
            return;
        }

        if(parent.color) {
            return;
        }

        Node grandParent = parent.parent;
        //case 2
        if(grandParent == null){
            parent.color = true;
            return;
        }

        Node uncle = getUncle(parent);
        //case 3
        if(uncle != null && !uncle.color) {
            parent.color = true;
            uncle.color = true;
            grandParent.color = false;

            fixRedBlackTiesAfterInsert(grandParent);
        }
        else if (parent == grandParent.left) {
            //case 4a:
            if(node == parent.right) {
                rotateLeft(parent);
                parent = node;
            }
            //case 5a:
            rotateRight(grandParent);
            parent.color = true;
            grandParent.color = false;
        }
        else {
            //case 4b:
            if(node == parent.left){
                rotateRight(parent);
                parent = node;
            }
            //case 5b:
            rotateLeft(grandParent);
            parent.color = true;
            grandParent.color = false;
        }
    }

    private  Node deleteNodeWithZeroOrOneChild(Node node) {
        if (node.left != null) {
            replaceParentsChild(node.parent, node, node.left);
            return node.left; // moved-up node
        }

        else if (node.right != null) {
            replaceParentsChild(node.parent, node, node.right);
            return node.right; // moved-up node
        }


        else {
            Node newChild = new NilNode();
            replaceParentsChild(node.parent, node, newChild);
            return newChild;
        }
    }

    private  Node findMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void deleteNode(RecordsReadDto readDto) {
        Node node = root;

        while(node != null ) {
            if(!node.data.isAfter(readDto.getFirst_date()) && !readDto.getFirst_date().isAfter(node.data)) {
                if(node.hashTable.find(readDto).searchByValue(readDto.toString()).getData()
                        .getLine().equals(readDto.toString())) {
                    ListInfo list =
                            node.hashTable.find(readDto).searchByValue(readDto.toString()).listDeleteItem(readDto);
                    node.hashTable.setFilledCells(node.hashTable.getFilledCells() - 1);
                    node.hashTable.getTable()[node.hashTable.getKey(readDto)] = list.head;

                    break;
                }
            }
            if(node.data.isAfter(readDto.getFirst_date())){
                node = node.left;
            }
            else {
                node = node.right;
            }
        }

        //case 1:
        if(node == null) {
            return;
        }

        if(node.hashTable.getFilledCells() == 0) {

            Node movedUpNode;
            boolean deletedNodeColor;

            //case 2:
            if (node.left == null || node.right == null) {
                movedUpNode = deleteNodeWithZeroOrOneChild(node);
                deletedNodeColor = node.color;
            }

            //case 3:
            else {
                Node inOrderSuccessor = findMinimum(node.right);
                node.data = inOrderSuccessor.data;
                node.hashTable = inOrderSuccessor.hashTable;
                movedUpNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor);
                deletedNodeColor = inOrderSuccessor.color;
            }
            if (deletedNodeColor) {
                fixRedBlackTiesAfterDelete(movedUpNode);
            }

            if (movedUpNode.getClass() == NilNode.class) {
                replaceParentsChild(movedUpNode.parent, movedUpNode, null);
            }
        }

    }
    private  void fixRedBlackTiesAfterDelete(Node node){
        // Case 1: Examined node is root, end of recursion
        if (node == root) {
            // Uncomment the following line if you want to enforce black roots (rule 2):
            // node.color = BLACK;
            return;
        }

        Node sibling = getSibling(node);

        // Case 2: Red sibling
        if (!sibling.color ) {
            handleRedSibling(node, sibling);
            sibling = getSibling(node); // Get new sibling for fall-through to cases 3-6
        }

        // Cases 3+4: Black sibling with two black children
        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = false;

            // Case 3: Black sibling with two black children + red parent
            if (!node.parent.color) {
                node.parent.color = true;
            }

            // Case 4: Black sibling with two black children + black parent
            else {
                fixRedBlackTiesAfterDelete(node.parent);
            }
        }

        // Case 5+6: Black sibling with at least one red child
        else {
            handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
        }
    }

    private  Node getSibling(Node node){
        Node parent = node.parent;
        if(node == parent.left){
            return parent.right;
        }
        else if(node == parent.right){
            return parent.left;
        }
        else{
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }

    private  boolean isBlack(Node node){
        return node == null || node.color;
    }

    private  void handleRedSibling(Node node, Node sibling) {
        // Recolor...
        sibling.color = true;
        node.parent.color = false;

        // ... and rotate
        if (node == node.parent.left) {
            rotateLeft(node.parent);
        } else {
            rotateRight(node.parent);
        }
        // немного не понял, отсутсвует послдений шаг
    }

    private  void handleBlackSiblingWithAtLeastOneRedChild(Node node, Node sibling) {
        boolean nodeIsLeftChild = node == node.parent.left;

        // Case 5: Black sibling with at least one red child + "outer nephew" is black

        if (nodeIsLeftChild && isBlack(sibling.right)) {
            sibling.left.color = true;
            sibling.color = false;
            rotateRight(sibling);
            sibling = node.parent.right;
        } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
            sibling.right.color = true;
            sibling.color = false;
            rotateLeft(sibling);
            sibling = node.parent.left;
        }

        // Case 6: Black sibling with at least one red child + "outer nephew" is red

        sibling.color = node.parent.color;
        node.parent.color = true;
        if (nodeIsLeftChild) {
            sibling.right.color = true;
            rotateLeft(node.parent);
        } else {
            sibling.left.color = true;
            rotateRight(node.parent);
        }
    }

    private  void fixRedBlackAfterDelete(Node node){

        //case 1
        if(node == root){
            node.color = true;
            return;
        }

        Node sibiling = getSibling(node);

        //case 2 red sibiling
        if(!sibiling.color){
            handleRedSibling(node, sibiling);
            sibiling = getSibling(node);
        }

        //case 3+4
        if(isBlack(sibiling.left) && isBlack(sibiling.right)){
            sibiling.color = false;

            //case3
            if(!node.parent.color){
                node.parent.color = true;
            }
            else {
                fixRedBlackTiesAfterDelete(node.parent);
            }
        }
        //case 5+6
        else{
            handleBlackSiblingWithAtLeastOneRedChild(node, sibiling);
        }

    }

    public String findRecord(RecordsReadDto readDto) {

        Node node = root;
        while(node != null) {
            if (node.data.equals(readDto.getFirst_date())) {



                return node.hashTable.find(readDto).searchByValue(readDto.toString()).toString();
            } else if (node.data.isAfter(readDto.getFirst_date())) {
                node = node.left;
            } else if (readDto.getFirst_date().isAfter(node.data)) {
                node = node.right;
            }
        }

        return null;
    }
}