import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import DukeObjects.Parser;
import DukeObjects.TaskList;
import DukeObjects.Task;
import DukeObjects.Ui;

class ParserTest {
    static TaskList tl;
    static Parser p;
    @BeforeAll
    static void setup() {
        tl = new TaskList();
        p = new Parser(tl, null);
    }

    @Test
    void test_parsing_delete_command() {
        tl.add(new Task("tester"));
        p.parseCommand("delete 1");
        assertEquals(0, tl.size());
    }

    @Test
    void test_parsing_event_command() {
        p.parseCommand("event test /at 4");
        assertEquals("[E][ ] test (at: 4)", tl.get(0).toString());
        tl.remove(0);
    }
}

class TaskStub extends Task {
    static int i = 0;
    TaskStub(String desc) {
        super(desc);
    }

    public static Task dispatchTaskCreation(String command) {
        i = 1;
        return new Task("testing");
    }
}

class UiStub extends Ui {
    static int i = 0;
    UiStub() {
        super();
    }

    public static void printErr(String command) {
        i = 1;
    }
}