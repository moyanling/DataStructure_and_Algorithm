package org.mo39.fmbh.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.mo39.fmbh.datastructure.binarytree.TreeNode;
import org.mo39.fmbh.datastructure.binarytree.TreeNode.LevelOrderSol;
import org.mo39.fmbh.datastructure.linkedlist.ListNode;

import com.google.common.base.Joiner;

public class Z {

  public static void printnb(Object obj) {
    System.out.print(obj);
  }

  public static void print(Object obj) {
    System.out.println(obj);
  }

  public static <T> void print(T[] arr) {
    print(Arrays.toString(arr));
  }

  public static void print(int[] arr) {
    print(Arrays.toString(arr));
  }

  public static <T> void print(ListNode<T> head) {
    while (head != null) {
      printnb(head + " -> ");
      head = head.next;
    }
    print("null");
  }

  public static <T> void print(Stack<T> stack) {
    while (!stack.isEmpty()) {
      printnb(stack.pop() + " -> ");
    }
    print("null");
  }

  public static <T> void print(TreeNode<T> root) {
    for (List<T> level : root.bfs(LevelOrderSol.ITERATIVE_SOLUTION_WITH_NULL)) {
      print(Joiner.on(',').join(level.stream().map(o -> o == null ? '#' : String.valueOf(o))
          .collect(Collectors.toList())));
    }

  }

  /**
   * Helper function that swaps two elements at position i and j in an array.
   *
   * @param arr
   * @param i
   * @param j
   */
  public static <T> void swap(T[] arr, int i, int j) {
    if (i == j) return;
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  /**
   * Helper function that swaps two elements at position i and j in an ArrayList.
   *
   * @param arr
   * @param i
   * @param j
   */
  public static <T> void swap(List<T> list, int i, int j) {
    if (i == j) return;
    list.set(j, list.set(i, list.get(j)));
  }

  /**
   * Assert that one linked list is equal to a given array.
   *
   * @param expected
   * @param head
   */
  public static <T> void verifyListNodes(T[] expected, ListNode<T> head) {
    for (int i = 0; i < expected.length; i++) {
      Assert.assertEquals(expected[i], head.val);
      head = head.next;
    }
    Assert.assertNull(head);
  }

  /**
   * Assert that two binary trees are equal.
   * <p>
   * Two binary tree are considered equal if they are structurally identical and the nodes have the
   * same value.
   * <p>
   * {@link TreeNode.LevelOrderSol#ITERATIVE_SOLUTION_WITH_NULL} can also be used as a solution. It
   * is not a fast solution but is valid (pass all the test cases on leetcode).
   *
   * @param root1
   * @param root2
   */
  public static <T> void verifyTreeNodes(TreeNode<T> p, TreeNode<T> q) {
    if (p == null || q == null) {
      Assert.assertEquals(p, q);
      return;
    }
    Assert.assertEquals(p.val, q.val);
    verifyTreeNodes(p.left, q.left);
    verifyTreeNodes(p.right, q.right);
  }


  private static final Predicate<Path> PREDICATE =
  //@formatter:off
    p ->
      Files.isRegularFile(p)
      && p.toString().matches(".*(datastructure|algorithm).*")
      && p.getFileName().toString().contains(new String("java"))
      && !p.getFileName().toString().contains(new String("Problem"))
      && !p.getFileName().toString().contains(new String("package-inpo")
    );
  //@formatter:on

  private static final Function<Path, String> MAPPER = //
      p -> p.getFileName().toString();

  private static String mapToString(Path p) {
    String str = p.getFileName().toString();
    i
  }



  /**
   * Get the table of content to the screen.
   *
   */
  private static void getTableOfContent() {
    Path root = Paths.get(System.getProperty("user.dir"));
    try (Stream<Path> paths = Files.walk(root)) {
      Stream<Path> newPaths = paths.filter(f -> PREDICATE.test(f));
      newPaths.forEach(filePath -> {
        System.out.println(filePath);
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Pretty print the directory tree and its file names.
   *
   * @param folder must be a folder.
   * @return
   */
  public static void printDirectoryTree(File folder) {
    int indent = 0;
    StringBuilder sb = new StringBuilder();
    printDirectoryTree(folder, indent, sb);
    print(sb.toString());
  }

  private static void printDirectoryTree(File folder, int indent, StringBuilder sb) {
    if (!folder.isDirectory()) { throw new IllegalArgumentException("folder is not a Directory"); }
    sb.append(getIndentString(indent));
    sb.append("+--");
    sb.append(folder.getName());
    sb.append("/");
    sb.append("\n");
    for (File file : folder.listFiles()) {
      if (file.isDirectory()) {
        printDirectoryTree(file, indent + 1, sb);
      } else {
        printFile(file, indent + 1, sb);
      }
    }

  }

  private static void printFile(File file, int indent, StringBuilder sb) {

    sb.append(getIndentString(indent));
    sb.append("+--");
    sb.append(file.getName());
    sb.append("\n");
  }

  private static String getIndentString(int indent) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < indent; i++) {
      sb.append("|  ");
    }
    return sb.toString();
  }

  private static final String TAB = "\\t";

  /**
   * Print table of content to the screen using a specific format.
   *
   */
  private static void print(Stream<Path> p) {
    StringBuilder sb = new StringBuilder();
  }

  public static void main(String[] args) {
    getTableOfContent();
    // printDirectoryTree(Paths.get(System.getProperty("user.dir")).toFile());
  }

}
