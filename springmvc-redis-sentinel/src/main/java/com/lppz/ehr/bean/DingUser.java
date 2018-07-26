package com.lppz.ehr.bean;

public class DingUser {
	private int id;
	
    private String userid;

    private String name;

    private Boolean active;

    private String tel;

    private String workPlace;

    private String remark;

    private String mobile;

    private String email;

    private String orderInDepts;

    private Boolean isAdmin;
    private Boolean isBoss;
    private Boolean isHide;

    private String department;

    private String position;

    private String avatar;

    private String jobnumber;

    private String extattr;
    private Boolean senior;
    private String sapid;
    private String isLeaderInDepts;
    private String initials;
    private String namePinyin;
	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getNamePinyin() {
		return namePinyin;
	}

	public void setNamePinyin(String namePinyin) {
		this.namePinyin = namePinyin;
	}

	public String getSapid() {
		return sapid;
	}

	public void setSapid(String sapid) {
		this.sapid = sapid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrderInDepts() {
        return orderInDepts;
    }

    public void setOrderInDepts(String orderInDepts) {
        this.orderInDepts = orderInDepts;
    }
    public String getIsLeaderInDepts() {
        return isLeaderInDepts;
    }

    public void setIsLeaderInDepts(String isLeaderInDepts) {
        this.isLeaderInDepts = isLeaderInDepts;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public String getExtattr() {
        return extattr;
    }

    public void setExtattr(String extattr) {
        this.extattr = extattr;
    }
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIsBoss() {
		return isBoss;
	}

	public void setIsBoss(Boolean isBoss) {
		this.isBoss = isBoss;
	}

	public Boolean getIsHide() {
		return isHide;
	}

	public void setIsHide(Boolean isHide) {
		this.isHide = isHide;
	}

	public Boolean getSenior() {
		return senior;
	}

	public void setSenior(Boolean senior) {
		this.senior = senior;
	}

	@Override
	public String toString() {
		return "DingUser [id=" + id + ", userid=" + userid + ", name=" + name + ", active=" + active + ", tel=" + tel
				+ ", workPlace=" + workPlace + ", remark=" + remark + ", mobile=" + mobile + ", email=" + email
				+ ", orderInDepts=" + orderInDepts + ", isAdmin=" + isAdmin + ", isBoss=" + isBoss + ", isHide="
				+ isHide + ", department=" + department + ", position=" + position + ", avatar=" + avatar
				+ ", jobnumber=" + jobnumber + ", extattr=" + extattr + ", senior=" + senior + ", sapid=" + sapid
				+ ", isLeaderInDepts=" + isLeaderInDepts + ", initials=" + initials + ", namePinyin=" + namePinyin
				+ "]";
	}


}
