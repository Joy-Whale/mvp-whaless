package me.whaless.app.data.entity.common;

/**
 * User: Joy
 * Date: 2017/5/22
 * Time: 11:07
 */
//@Entity(
//		// 如果你有超过一个的数据库结构，可以通过这个字段来区分
//		// 该实体属于哪个结构
//		// schema = "alarm",
//		// 实体是否激活的标志，激活的实体有更新，删除和刷新的方法
//		active = true,
//		// 确定数据库中表的名称
//		// 表名称默认是实体类的名称
//		nameInDb = "DICTIONARY",
//		// Define indexes spanning multiple columns here.
//		indexes = {
//				@Index(value = "name ASC")
//		},
//		// DAO是否应该创建数据库表的标志(默认为true)
//		// 如果你有多对一的表，将这个字段设置为false
//		// 或者你已经在GreenDAO之外创建了表，也将其置为false
//		createInDb = true
//		//
//)
public class TestEntity {

//	@Id(autoincrement = true)
	private Long id;
//	@Property
	private String name;
	/** Used to resolve relations */

	public TestEntity() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
