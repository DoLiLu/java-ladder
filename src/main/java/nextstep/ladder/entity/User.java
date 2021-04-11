package nextstep.ladder.entity;

public class User {
    private final String name;
    private final int MAX_NAME_SIZE = 5;

    public User(String name) {
        nameValid(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void nameValid(String name) {

        if (name.length() > MAX_NAME_SIZE) {
            throw new IllegalArgumentException("이름은 5자를 초과 할 수 없습니다.");
        }
    }
}