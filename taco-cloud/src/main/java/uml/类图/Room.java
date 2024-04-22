package uml.类图;

// Room 类
public class Room {
    private SchoolBuilding belongsTo; // 组合关系，房间所属的学校建筑

    public void setBelongsTo(SchoolBuilding building) {
        this.belongsTo = building;
    }

    // 其他房间相关方法...

    public SchoolBuilding getBelongsTo() {
        return belongsTo;
    }
}
