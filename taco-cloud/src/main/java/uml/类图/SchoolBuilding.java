package uml.类图;

import java.util.ArrayList;
import java.util.List;

// SchoolBuilding 类
public class SchoolBuilding {
    private List<Room> rooms; // 组合关系，学校建筑拥有的房间集合

    public SchoolBuilding() {
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
        room.setBelongsTo(this); // 设置房间所属的学校建筑
    }

    // 其他学校建筑相关方法...
}
