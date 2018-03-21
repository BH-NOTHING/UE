var sql="select * from tp_template_type";
var pFormatterSql="select * from tp_template_type";
var orgFormatterSql="select * from pwp_org";
var deviceFormatterSql="select * from mp_device";
var formatter = {
		formatter:{	
			"org_id":db.formatterUtil.sql(orgFormatterSql,"org_id","org_name"),
			"device_id":db.formatterUtil.sql(deviceFormatterSql,"id","device_name"),
			"pid":db.formatterUtil.sql(pFormatterSql,"id","tname")
		}
	}

var data=db.queryService.query('select * from tp_template_type',param,formatter);
//console.log(data);
return data;