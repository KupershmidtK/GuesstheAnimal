package animals;

public class GuessTree {
    private TreeNode rootNode;

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public TreeNode nextPositiveNode(TreeNode node) {
        if (node != null) {
            return node.getYes();
        }
        return null;
    }

    public TreeNode nextNegativeNode(TreeNode node) {
        if (node != null) {
            return node.getNo();
        }
        return null;
    }
}