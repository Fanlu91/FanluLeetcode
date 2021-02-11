package designpattern.behavioral.memento;

import java.util.Scanner;

/**
 * Originator：发起者，负责创建一个备忘录，并且可以记录、恢复自身的内部状态。
 * 可以根据需要决定Memento保存自身的那些内部状态
 *
 * Memento：备忘录，用于存储Originator的状态，防止Originator以外的的对象访问Memento
 *
 * Caretaker：备忘录管理者，负责存储备忘录，
 * 不能对备忘录的内容进行操作和访问，只能够将备忘录传递给其他对象。
 *t
 */
public class Application {
    public static void main(String[] args) {
        InputText inputText = new InputText();
        SnapshotHolder snapshotsHolder = new SnapshotHolder();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals(":list")) {
                System.out.println(inputText.getText());
            } else if (input.equals(":undo")) {
                InputText snapshot = snapshotsHolder.popSnapshot();
                inputText.setText(snapshot.getText());
            } else {
                snapshotsHolder.pushSnapshot(inputText);
                inputText.append(input);
            }
        }
    }
}
