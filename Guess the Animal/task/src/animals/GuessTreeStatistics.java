package animals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GuessTreeStatistics {
    private final GuessTree tree;
    private final TreeInfo info = new TreeInfo();

    public GuessTreeStatistics(GuessTree tree) {
        this.tree = tree;
    }

    public List<String> getAllAnimals() {
        List<String> list = new ArrayList<>();
        findAllAnimals(tree.getRootNode(), list);
        list.sort(Comparator.naturalOrder());
        return list;
    }

    public List<String> getAnimalFacts(String name) {
        List<String> list = new ArrayList<>();
        findAnimal(tree.getRootNode(), name, list);
        Collections.reverse(list);
        return list;
    }

    public TreeInfo getTreeInfo() {
        clearTreeInfo();
        info.rootNode = tree.getRootNode().getData();
        gatherTreeInfo(tree.getRootNode(), 0);
        info.avgAnimalDepth = info.avgAnimalDepth / info.numberOfAnimals;
        return info;
    }

    public List<String> printGuessTree() {
        List<String> list = new ArrayList<>();
        printTree(tree.getRootNode(), 0, list);
        return list;
    }

    private void clearTreeInfo() {
        info.rootNode = null;
        info.numberOfNodes = 0;
        info.numberOfAnimals = 0;
        info.numberOfStatements = 0;
        info.heightOfTree = 0;
        info.minAnimalDepth = Integer.MAX_VALUE;
        info.avgAnimalDepth = 0;
    }


    private void gatherTreeInfo(TreeNode node, int treeLevel) {
        info.numberOfNodes++;
        if (node.isAnimal()) {
            info.numberOfAnimals++;
            info.avgAnimalDepth += treeLevel;
            if (treeLevel < info.minAnimalDepth) {
                info.minAnimalDepth = treeLevel;
            }
            if (treeLevel > info.heightOfTree) {
                info.heightOfTree = treeLevel;
            }
            return;
        }
        info.numberOfStatements++;
        gatherTreeInfo(node.getYes(), treeLevel + 1);
        gatherTreeInfo(node.getNo(), treeLevel + 1);
    }

    private void findAllAnimals(TreeNode node, List<String> list) {
        if (node.isAnimal()) {
            list.add(node.getData());
            return;
        }
        findAllAnimals(node.getNo(), list);
        findAllAnimals(node.getYes(), list);
    }

    private boolean findAnimal(TreeNode node, String name, List<String> list) {
        if (node.isAnimal()) {
            return name.equals(node.getData());
        }
        if (findAnimal(node.getNo(), name, list)) {
            list.add(GameUtils.negativeStatement(node.getData()));
            return true;
        }
        if (findAnimal(node.getYes(), name, list)) {
            list.add(node.getData());
            return true;
        }
        return false;
    }

    private void printTree(TreeNode node, int treeLevel, List<String> list) {
        if (node.isAnimal()) {
            list.add(" ".repeat(treeLevel) + node.getData());
        } else {
            list.add(" ".repeat(treeLevel) + GameUtils.makeQuestion(node.getData()));
            printTree(node.getYes(), treeLevel + 1, list);
            printTree(node.getNo(), treeLevel + 1, list);
        }
    }
}
