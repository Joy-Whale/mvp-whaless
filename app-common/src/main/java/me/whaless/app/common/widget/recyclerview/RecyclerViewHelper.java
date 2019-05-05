package me.whaless.app.common.widget.recyclerview;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * **********************
 * Author: yu
 * Date:   2015/12/12
 * Time:   18:26
 * **********************
 */
public class RecyclerViewHelper {

	public static int findFirstCompleteVisibleItemPosition(RecyclerView recyclerView) {
		return findFirstVisibleItemPosition(recyclerView, true);
	}

	public static int findFirstVisibleItemPosition(RecyclerView recyclerView, boolean complete) {
		int firstItem = 0, firstCompleteItem = 0;
		if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
			GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
			firstItem = layoutManager.findFirstVisibleItemPosition();
			firstCompleteItem = layoutManager.findFirstCompletelyVisibleItemPosition();
		} else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
			LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
			firstItem = layoutManager.findFirstVisibleItemPosition();
			firstCompleteItem = layoutManager.findFirstCompletelyVisibleItemPosition();
		} else if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
			StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
			firstItem = layoutManager.findFirstVisibleItemPositions(null)[0];
			firstCompleteItem = layoutManager.findFirstCompletelyVisibleItemPositions(null)[0];
		}
		return complete ? Math.max(firstItem, firstCompleteItem) : Math.min(firstItem, firstCompleteItem  == -1 ? firstItem : firstCompleteItem);
	}

	public static int findLastCompleteVisibleItemPosition(RecyclerView recyclerView) {
		return findLastVisibleItemPosition(recyclerView, true);
	}

	public static int findLastVisibleItemPosition(RecyclerView recyclerView, boolean complete) {
		int lastItem = 0, lastCompleteItem = 0;
		if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
			GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
			lastItem = layoutManager.findLastVisibleItemPosition();
			lastCompleteItem = layoutManager.findLastCompletelyVisibleItemPosition();
		} else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
			LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
			lastItem = layoutManager.findLastVisibleItemPosition();
			lastCompleteItem = layoutManager.findLastCompletelyVisibleItemPosition();
		} else if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
			StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
			lastItem = layoutManager.findLastVisibleItemPositions(null)[0];
			lastCompleteItem = layoutManager.findLastCompletelyVisibleItemPositions(null)[0];
		}
		return complete ? Math.max(lastItem, lastCompleteItem) : Math.min(lastItem, lastCompleteItem);
	}

	public static int findFirstVisibleItemOffset(RecyclerView recyclerView){
		if(recyclerView == null || recyclerView.getLayoutManager() == null || recyclerView.getLayoutManager().getChildAt(0) == null)
			return 0;
		return -recyclerView.getLayoutManager().getChildAt(0).getTop();
	}
}
