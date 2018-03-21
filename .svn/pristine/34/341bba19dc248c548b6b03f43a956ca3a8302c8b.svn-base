var openD3 = function(newparam) {
    this.paramdata = newparam.paramdata||[];
    this.dataset = [];
    this.lines = []; //保存折线图对象
    this.xMarks = [];
    this.lineNames = []; //保存系列名称
    this.lineColor = newparam.lineColor||["#F00", "#09F", "#0F0"];
    this.w = 600;
    this.h = 400;
    this.padding = 40;
    this.currentLineNum = 0;

    //用一个变量存储标题和副标题的高度，如果没有标题什么的，就为0
    this.head_height = this.padding;
    this.title = "收支平衡统计图";
    this.subTitle = "2017年1月 至 2017年12月";

    //用一个变量计算底部的高度，如果不是多系列，就为0
    this.foot_height = this.padding;
    this.foot_height = this.padding;
    this.legend = '';
    this.svg = ""; //定义画布
    //横坐标轴比例尺
    this.xScale = "";
    //纵坐标轴比例尺
    this.yScale = "";
    //定义横轴
    this.xAxis = "";
    //添加横坐标轴
    this.xBar = "";
    this.xInner = "";
    //添加横轴网格线
    this.xInnerBar = "";
    //定义纵轴网格线
    this.yInner = "";
    //添加纵轴网格线
    this.yInnerBar = "";
    this.yBar = "";
    this.yInnerBar = "";
    this.yAxis = "";
    this.test = function() {
        var a = '';
    }

}

//定义折线类
window.openD3.prototype = {
    CrystalLineObject: function(_this) {
        this.group = null;
        this.path = null;
        this.oldData = [];
        this._dataset = _this.dataset;
        var mythis = _this;
        var _dataset = _this.dataset;
        var _svg = _this.svg;
        var _yScale = _this.yScale;
        var _xScale = _this.xScale;
        var _lineColor = _this.lineColor;
        this.init = function(id) {
            var arr = _dataset[id];
            this.group = _svg.append("g");
            var line = d3.svg.line().x(function(d, i) {
                return _xScale(i);
            }).y(function(d) {
                return _yScale(d);
            });

            //添加折线
            this.path = this.group.append("path").attr("d", line(arr)).style("fill", "none").style("stroke-width", 1).style("stroke", _lineColor[id]).style("stroke-opacity", 0.9);

            //添加系列的小圆点
            this.group.selectAll("circle").data(arr).enter().append("circle").attr("cx",
                function(d, i) {
                    return _xScale(i);
                }).attr("cy",
                function(d) {
                    return _yScale(d);
                }).attr("r", 5).attr("fill", _lineColor[id]);
            this.oldData = arr;
        };

        //动画初始化方法
        this.movieBegin = function(id) {
            var arr = mythis.dataset[i];
            //补足/删除路径
            var olddata = this.oldData;
            var line = d3.svg.line().x(function(d, i) {
                if (i >= olddata.length) return mythis.w - mythis.padding;
                else return mythis.xScale(i);
            }).y(function(d, i) {
                if (i >= olddata.length) return mythis.h - mythis.foot_height;
                else return mythis.yScale(olddata[i]);
            });

            //路径初始化
            this.path.attr("d", line(arr));

            //截断旧数据
            var tempData = olddata.slice(0, arr.length);
            var circle = this.group.selectAll("circle").data(tempData);

            //删除多余的圆点
            circle.exit().remove();

            //圆点初始化，添加圆点,多出来的到右侧底部
            this.group.selectAll("circle").data(arr).enter().append("circle").attr("cx",
                function(d, i) {
                    if (i >= olddata.length) return mythis.w - mythis.padding;
                    else return mythis.xScale(i);
                }).attr("cy",
                function(d, i) {
                    if (i >= olddata.length) return mythis.h - mythis.foot_height;
                    else return mythis.yScale(d);
                }).attr("r", 5).attr("fill", mythis.lineColor[id]);

            this.oldData = arr;
        };

        //重绘加动画效果
        this.reDraw = function(id, _duration) {
            var arr = mythis.dataset[i];
            var line = d3.svg.line().x(function(d, i) {
                return mythis.xScale(i);
            }).y(function(d) {
                return mythis.yScale(d);
            });

            //路径动画
            this.path.transition().duration(_duration).attr("d", line(arr));

            //圆点动画
            this.group.selectAll("circle").transition().duration(_duration).attr("cx",
                function(d, i) {
                    return mythis.xScale(i);
                }).attr("cy",
                function(d) {
                    return mythis.yScale(d);
                })
        };

        //从画布删除折线
        this.remove = function() {
            this.group.remove();
        };
    },
    //添加图例
    addLegend: function() {
        var _this=this;
        var textGroup = _this.legend.selectAll("text").data(_this.lineNames);
        var _lineColor = _this.lineColor;
        textGroup.exit().remove();

        this.legend.selectAll("text").data(_this.lineNames).enter().append("text").text(function(d) {
            return d;
        }).attr("class", "legend").attr("x",
            function(d, i) {
                return i * 100;
            }).attr("y", 0).attr("fill",
            function(d, i) {
                return _lineColor[i];
            });

        var rectGroup = this.legend.selectAll("rect").data(_this.lineNames);

        rectGroup.exit().remove();

        this.legend.selectAll("rect").data(_this.lineNames).enter().append("rect").attr("x",
            function(d, i) {
                return i * 100 - 20;
            }).attr("y", -10).attr("width", 12).attr("height", 12).attr("fill",
            function(d, i) {
                return _lineColor[i];
            });

        this.legend.attr("transform", "translate(" + ((_this.w - _this.lineNames.length * 100) / 2) + "," + (_this.h - 10) + ")");
    },
    //产生随机数据
    getData: function(lineNum, dataNum,paramdata) {
        var _this=this;
        var lineNums = Math.round(Math.random() * 10) % 3 + 1; //线条个数
        lineNum = lineNum||lineNums;
        var dataNums = Math.round(Math.round(Math.random() * 10)) + 5; //数据个数
        dataNum = dataNum||dataNums;
        this.oldData = _this.dataset;

        this.dataset = [];
        this.xMarks = [];
        this.lineNames = [];
        if(!paramdata){
            for (var i = 0; i < dataNum; i++) {
                this.xMarks.push(i+"月份");
            }
            for (i = 0; i < lineNum; i++) {
                var tempArr = [];
                for (j = 1; j < dataNum; j++) {
                    var mathNum=Math.round(Math.random() * this.h);
                    tempArr.push(mathNum);
                }
                this.dataset.push(tempArr);
                this.lineNames.push("系列" + i);
            }
        }else{
            this.dataset=paramdata;
            for (var ii = 0; ii < paramdata[0].length; ii++) {
                this.xMarks.push(ii+"月份");
            }
            for (i = 0; i < paramdata.length; i++) {
                this.lineNames.push("系列" + i);
            }
        }
        console.log(JSON.stringify(this.lineNames));
        console.log(JSON.stringify(this.dataset));
    },
    //取得多维数组最大值
    getMaxdata: function(arr) {
        this.maxdata = 0;
        for (i = 0; i < arr.length; i++) {
            this.maxdata = d3.max([this.maxdata, d3.max(arr[i])]);
        }
        return this.maxdata;
    },
    //重新作图
    drawChart: function() {
        var _duration = 1000;
        var _this = this;
        _this.getData();

        _this.addLegend();

        //设置线条动画起始位置
        var lineObject = new _this.CrystalLineObject(_this);

        for (i = 0; i < _this.dataset.length; i++) {
            if (i < _this.currentLineNum) {
                //对已有的线条做动画
                lineObject = _this.lines[i];
                lineObject.movieBegin(i);
            } else {
                //如果现有线条不够，就加上一些
                var newLine = new _this.CrystalLineObject(_this);
                newLine.init(i);
                _this.lines.push(newLine);
            }
        }

        //删除多余的线条，如果有的话
        if (_this.dataset.length < _this.currentLineNum) {
            for (i = _this.dataset.length; i < _this.currentLineNum; i++) {
                lineObject = _this.lines[i];
                lineObject.remove();
            }
            _this.lines.splice(_this.dataset.length, _this.currentLineNum - _this.dataset.length);
        }

        _this.maxdata = _this.getMaxdata(_this.dataset);
        _this.newLength = _this.dataset[0].length;

        //横轴数据动画
        _this.xScale.domain([0, _this.newLength - 1]);
        _this.xAxis.scale(_this.xScale).ticks(_this.newLength);
        _this.xBar.transition().duration(_duration).call(_this.xAxis);
        _this.xBar.selectAll("text").text(function(d) {
            return _this.xMarks[d];
        });
        _this.xInner.scale(_this.xScale).ticks(_this.newLength);
        _this.xInnerBar.transition().duration(_this._duration).call(_this.xInner);

        //纵轴数据动画
        _this.yScale.domain([0, _this.maxdata]);
        _this.yBar.transition().duration(_duration).call(_this.yAxis);
        _this.yInnerBar.transition().duration(_duration).call(_this.yInner);

        //开始线条动画
        for (i = 0; i < _this.lines.length; i++) {
            lineObject = _this.lines[i];
            lineObject.reDraw(i, _duration);
        }

        _this.currentLineNum = _this.dataset.length;
        _this.dataLength = _this.newLength;
    },
    //作图
    openChart: function() {
        var _this = this;
        //模拟数据
        this.getData("", "", this.paramdata);

        //判断是否多维数组，如果不是，则转为多维数组，这些处理是为了处理外部传递的参数设置的，现在数据标准，没什么用
        if (! (this.dataset[0] instanceof Array)) {
            var tempArr = [];
            tempArr.push(this.dataset);
            this.dataset = tempArr;
        }

        //保存数组长度，也就是系列的个数
        this.currentLineNum = this.dataset.length;

        //图例的预留位置
        this.foot_height += 25;

        this.svg = d3.select("body").append("svg").attr("width", this.w).attr("height", this.h);

        //添加背景
        this.svg.append("g").append("rect").attr("x", 0).attr("y", 0).attr("width", this.w).attr("height", this.h).style("fill", "#FFF").style("stroke-width", 2).style("stroke", "#E7E7E7");

        //添加标题
        if (this.title != "") {
            this.svg.append("g").append("text").text(this.title).attr("class", "title").attr("x", this.w / 2).attr("y", this.head_height);

            this.head_height += 30;
        }

        //添加副标题
        if (this.subTitle != "") {
            this.svg.append("g").append("text").text(this.subTitle).attr("class", "subTitle").attr("x", this.w / 2).attr("y", this.head_height);

            this.head_height += 20;
        }

        this.maxdata = this.getMaxdata(this.dataset);

        //横坐标轴比例尺
        this.xScale = d3.scale.linear().domain([0, this.dataset[0].length - 1]).range([this.padding, this.w - this.padding]);

        //纵坐标轴比例尺
        this.yScale = d3.scale.linear().domain([0, this.maxdata]).range([this.h - this.foot_height, this.head_height]);

        //定义横轴网格线
        this.xInner = d3.svg.axis().scale(this.xScale).tickSize( - (this.h - this.head_height - this.foot_height), 0, 0).tickFormat("").orient("bottom").ticks(this.dataset[0].length);

        //添加横轴网格线
        this.xInnerBar = this.svg.append("g").attr("class", "inner_line").attr("transform", "translate(0," + (this.h - this.padding) + ")").call(this.xInner);

        //定义纵轴网格线
        this.yInner = d3.svg.axis().scale(this.yScale).tickSize( - (this.w - this.padding * 2), 0, 0).tickFormat("").orient("left").ticks(10);

        //添加纵轴网格线
        this.yInnerBar = this.svg.append("g").attr("class", "inner_line").attr("transform", "translate(" + this.padding + ",0)").call(this.yInner);

        //定义横轴
        this.xAxis = d3.svg.axis().scale(this.xScale).orient("bottom").ticks(this.dataset[0].length);

        //添加横坐标轴
        this.xBar = this.svg.append("g").attr("class", "axis").attr("transform", "translate(0," + (this.h - this.foot_height) + ")").call(this.xAxis);
        var _xMarks = this.xMarks;
        //通过编号获取对应的横轴标签
        this.xBar.selectAll("text").text(function(d) {
            return _xMarks[d];
        });

        //定义纵轴
        this.yAxis = d3.svg.axis().scale(this.yScale).orient("left").ticks(10);

        //添加纵轴
        this.yBar = this.svg.append("g").attr("class", "axis").attr("transform", "translate(" + this.padding + ",0)").call(this.yAxis);

        //添加图例
        this.legend = this.svg.append("g");

        this.addLegend();

        //添加折线
        this.lines = [];
        for (i = 0; i < this.currentLineNum; i++) {
            var newLine = new this.CrystalLineObject(_this);
            newLine.init(i);
            this.lines.push(newLine);
        }

    }
}