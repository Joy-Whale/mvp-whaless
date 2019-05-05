package me.whaless.app.domain.model;

/**
 * User: JiYu
 * Date: 2016-09-06
 * Time: 15-57
 */

public class Listable {

	public static Listable newInstance() {
		return new Listable();
	}

	public static Listable newInstance(String id) {
		return new Listable(id);
	}

	// 全部类型的标识
	public static final String TYPE_ALL = "all";

	public interface LoadType {
		int GET = 1;
		int REFRESH = 2;
		int MORE = 3;
	}

	public enum Sort {
		New("1"), Hot("2"), Past("3");

		private String value;

		Sort(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	private static final int PAGE_SIZE_DEFAULT = 10;

	private static final int FIRST_PAGE = 1;
	private String id;

	//  page与realPage的区别在与：page是用来保存服务器需要的页数，而realPage为当前真实的页数
	private int page;
	private int pageSize = PAGE_SIZE_DEFAULT;
	private String type;
	private Sort sort = Sort.New;

	private int realPage;
	private int loadType = LoadType.GET;

	// 关键字
	private String keyWord;

	public Listable() {
		loadType = LoadType.GET;
		page = FIRST_PAGE;
	}

	public Listable(int pageSize) {
		this();
		this.pageSize = pageSize;
	}

	public Listable(String id) {
		this();
		this.id = id;
	}

	public Listable(String id, String type) {
		this();
		this.id = id;
		this.type = type;
	}

	public Listable(int page, int pageSize, String id, String type) {
		this.id = id;
		this.page = page;
		this.pageSize = pageSize;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getLoadType() {
		return loadType;
	}

	public void setLoadType(int type) {
		this.loadType = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void nextPage() {
		page++;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public int getRealPage() {
		return realPage;
	}

	public void setRealPage(int realPage) {
		this.realPage = realPage;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	/**
	 * Deprecated use get(),more(),refresh() replace
	 * @param type 类型
	 * @param <T>  泛型
	 */
	@Deprecated
	public <T extends Listable> T load(int type) {
		this.loadType = type;
		if (type != LoadType.MORE) {
			page = FIRST_PAGE;
			realPage = FIRST_PAGE;
		}
		return (T) this;
	}

	public <T extends Listable> T refreshAppend(){
		this.loadType = LoadType.REFRESH;
		return (T) this;
	}

	public <T extends Listable> T more() {
		return load(LoadType.MORE);
	}

	public <T extends Listable> T refresh() {
		return load(LoadType.REFRESH);
	}

	public <T extends Listable> T get() {
		return load(LoadType.GET);
	}

	public <T extends Listable> T type(String type) {
		this.type = type;
		return (T) this;
	}

	/**
	 * 是否为初次加载？  即get
	 */
	public boolean isInitialLoad() {
		return loadType == LoadType.GET;
	}

	/**
	 * 是否为首页数据
	 * GET 和 REFRESH都为首页数据
	 */
	public boolean isFirstPage() {
		return loadType < LoadType.MORE;
	}

	public void validateMore(){
		page ++;
	}

	@Override
	public String toString() {
		return "?id=" + id + "&page=" + page + "&pageSize=" + pageSize + "&type=" + type;
	}
}
