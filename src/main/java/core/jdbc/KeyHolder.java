package core.jdbc;

public class KeyHolder {

    // insert된 객체의 id값을 저장할 인스턴수 변수
    private Long id;

    public KeyHolder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
