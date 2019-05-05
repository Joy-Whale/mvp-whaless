package me.whaless.app.presentation;

/**
 * User: Joy
 * DateModel: 2016-11-20
 * Time: 17:52
 */

public class Constants {
	public static final String NAMED_GENERAL_FEEDBACK = "general_feedback";
	public static final String NAMED_GENERAL_VERSION = "general_version";

	public static final String NAMED_USER_SIGN_IN = "user_sign_in";
	public static final String NAMED_USER_SIGN_UP = "user_sign_up";
	public static final String NAMED_USER_VIP = "user_vip";
	public static final String NAMED_USER_VIP_PLANS = "user_vip_plans";
	public static final String NAMED_USER_VIP_PLAN_UPDATE_CHECK = "user_vip_plan_update_check";
	public static final String NAMED_USER_VIP_PLAN_UPDATE = "user_vip_plan_update";
	public static final String NAMED_USER_VIP_ENABLE = "user_vip_enable";
	public static final String NAMED_USER_CREATE_ORDER = "user_create_order";
	public static final String NAMED_USER_ORDER_LIST = "user_order_list";
	public static final String NAMED_USER_INFO = "user_info";
	public static final String NAMED_USER_TRAFFIC = "user_traffic";
	public static final String NAMED_USER_SIGN_INFO = "user_sign_info";
	public static final String NAMED_USER_SIGN = "user_sign";
	public static final String NAMED_USER_COINS_LOGS = "user_coins_logs";
	public static final String NAMED_USER_COINS_INFO = "user_coins_info";
	public static final String NAMED_USER_COINS_VIP_PLANS = "user_coins_vip_plans";
	public static final String NAMED_USER_COINS_PLAN_EXCHANGE = "user_coins_plan_exchange";
	public static final String NAMED_USER_INVITE_CODE = "user_invite_code";


	public interface RxTag {
		// 连接vpn
		String VPN_CONNECT = "vpn_connect";
		// 断开vpn
		String VPN_DISCONNECT = "vpn_disconnect";
		// vpn连接状态改变
		String VPN_CONNECT_STATE_CHANGED = "vpn_state_changed";
		// vpn当前选中的密钥发生改变
		String VPN_SECRET_CHANGED = "vpn_secret_changed";
		// vpn当前选中的line发生改变
		String VPN_LINE_CHANGED = "vpn_line_changed";
		// 当前的vip发生改变
		String VIP_CHANGED = "vip_changed";
		// 当前的coins发生改变
		String COINS_CHANGED = "coins_changed";

		String LOGIN_STATE_CHANGED = "login_changed";
	}

}
