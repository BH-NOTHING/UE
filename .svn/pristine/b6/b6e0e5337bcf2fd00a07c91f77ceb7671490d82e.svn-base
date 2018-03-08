var sql="select * from tp_template_type where pid=0 or pid is null or pid=''";
var orgFormatterSql="select * from pwp_org";
var deviceFormatterSql="select * from mp_device";
var formatter = {
		formatter:{	
			"org_id":db.formatterUtil.sql(orgFormatterSql,"org_id","org_name"),
			"device_id":db.formatterUtil.sql(deviceFormatterSql,"id","device_name")
		}
	}

var data=db.queryService.query(sql,param,formatter);
//console.log(data);
return data;