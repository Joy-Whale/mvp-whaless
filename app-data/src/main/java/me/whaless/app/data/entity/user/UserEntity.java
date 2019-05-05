package me.whaless.app.data.entity.user;

import com.alibaba.fastjson.annotation.JSONField;

public class UserEntity {

	private String id;
	private String username;
	private String nickname;
	private int gender;
	private String avatar;
	private String email;
	private int type;
	@JSONField(name = "vip_type")
	private int vip;
	@JSONField(name = "vip_name")
	private String vipName;
	@JSONField(name = "vip_over_time")
	private long vipOverTime;
	@JSONField(name = "vpn_keep_time")
	private String vpnKeepTime;
	@JSONField(name = "vpn_bandwidth")
	private String vpnBandwidth;
	@JSONField(name = "vpn_netflow")
	private String vpnNetflow;
	@JSONField(name = "traffic_flow")
	private long trafficFlow;
	@JSONField(name = "traffic_flow_limited_begin")
	private long trafficFlowLimitedBegin;
	@JSONField(name = "traffic_duration")
	private long trafficDuration;
	// 最后签到时间
	@JSONField(name = "last_sign_time")
	private long lastSignTime;

	public UserEntity() {

	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getGender() {
		return gender;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public String getVipName() {
		return vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public long getVipOverTime() {
		return vipOverTime;
	}

	public void setVipOverTime(long vipOverTime) {
		this.vipOverTime = vipOverTime;
	}

	public String getVpnKeepTime() {
		return vpnKeepTime;
	}

	public void setVpnKeepTime(String vpnKeepTime) {
		this.vpnKeepTime = vpnKeepTime;
	}

	public String getVpnBandwidth() {
		return vpnBandwidth;
	}

	public void setVpnBandwidth(String vpnBandwidth) {
		this.vpnBandwidth = vpnBandwidth;
	}

	public String getVpnNetflow() {
		return vpnNetflow;
	}

	public void setVpnNetflow(String vpnNetflow) {
		this.vpnNetflow = vpnNetflow;
	}

	public long getTrafficFlow() {
		return trafficFlow;
	}

	public void setTrafficFlow(long trafficFlow) {
		this.trafficFlow = trafficFlow;
	}

	public long getTrafficFlowLimitedBegin() {
		return trafficFlowLimitedBegin;
	}

	public void setTrafficFlowLimitedBegin(long trafficFlowLimitedBegin) {
		this.trafficFlowLimitedBegin = trafficFlowLimitedBegin;
	}

	public long getTrafficDuration() {
		return trafficDuration;
	}

	public void setTrafficDuration(long trafficDuration) {
		this.trafficDuration = trafficDuration;
	}

	public long getLastSignTime() {
		return lastSignTime;
	}

	public void setLastSignTime(long lastSignTime) {
		this.lastSignTime = lastSignTime;
	}
}
