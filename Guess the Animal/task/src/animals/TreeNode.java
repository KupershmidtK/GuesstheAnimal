package animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNode {
    private String data;
    private TreeNode yes;
    private TreeNode no;

    public TreeNode() { } // need for TreeSaver

    public TreeNode(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public TreeNode getYes() {
        return yes;
    }

    public void setYes(TreeNode yes) {
        this.yes = yes;
    }

    public TreeNode getNo() {
        return no;
    }

    public void setNo(TreeNode no) {
        this.no = no;
    }

    @JsonIgnore
    public boolean isAnimal() {
        return yes == null && no == null;
    }
}
