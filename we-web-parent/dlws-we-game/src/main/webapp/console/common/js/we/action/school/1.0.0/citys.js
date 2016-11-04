define(function(require, exports, module){
	/**
	 * 选择城市用；
	 *
	 */
	var citys = [ {
		organization:[ {
			organization:[],
			orgNo:"110101",
			name:"东城区"
		}, {
			organization:[],
			orgNo:"110102",
			name:"西城区"
		}, {
			organization:[],
			orgNo:"110103",
			name:"崇文区"
		}, {
			organization:[],
			orgNo:"110104",
			name:"宣武区"
		}, {
			organization:[],
			orgNo:"110105",
			name:"朝阳区"
		}, {
			organization:[],
			orgNo:"110106",
			name:"丰台区"
		}, {
			organization:[],
			orgNo:"110107",
			name:"石景山区"
		}, {
			organization:[],
			orgNo:"110108",
			name:"海淀区"
		}, {
			organization:[],
			orgNo:"110109",
			name:"门头沟区"
		}, {
			organization:[],
			orgNo:"110111",
			name:"房山区"
		}, {
			organization:[],
			orgNo:"110112",
			name:"通州区"
		}, {
			organization:[],
			orgNo:"110113",
			name:"顺义区"
		}, {
			organization:[],
			orgNo:"110114",
			name:"昌平区"
		}, {
			organization:[],
			orgNo:"110115",
			name:"大兴区"
		}, {
			organization:[],
			orgNo:"110116",
			name:"怀柔区"
		}, {
			organization:[],
			orgNo:"110117",
			name:"平谷区"
		}, {
			organization:[],
			orgNo:"110228",
			name:"密云县"
		}, {
			organization:[],
			orgNo:"110229",
			name:"延庆县"
		} ],
		orgNo:"11",
		name:"北京"
	}, {
		organization:[ {
			organization:[],
			orgNo:"120101",
			name:"和平区"
		}, {
			organization:[],
			orgNo:"120102",
			name:"河东区"
		}, {
			organization:[],
			orgNo:"120103",
			name:"河西区"
		}, {
			organization:[],
			orgNo:"120104",
			name:"南开区"
		}, {
			organization:[],
			orgNo:"120105",
			name:"河北区"
		}, {
			organization:[],
			orgNo:"120106",
			name:"红桥区"
		}, {
			organization:[],
			orgNo:"120107",
			name:"塘沽区"
		}, {
			organization:[],
			orgNo:"120108",
			name:"汉沽区"
		}, {
			organization:[],
			orgNo:"120109",
			name:"大港区"
		}, {
			organization:[],
			orgNo:"120110",
			name:"东丽区"
		}, {
			organization:[],
			orgNo:"120111",
			name:"西青区"
		}, {
			organization:[],
			orgNo:"120112",
			name:"津南区"
		}, {
			organization:[],
			orgNo:"120113",
			name:"北辰区"
		}, {
			organization:[],
			orgNo:"120114",
			name:"武清区"
		}, {
			organization:[],
			orgNo:"120115",
			name:"宝坻区"
		}, {
			organization:[],
			orgNo:"120221",
			name:"宁河县"
		}, {
			organization:[],
			orgNo:"120223",
			name:"静海县"
		}, {
			organization:[],
			orgNo:"120225",
			name:"蓟县"
		} ],
		orgNo:"12",
		name:"天津"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"130102",
				name:"长安区"
			}, {
				organization:[],
				orgNo:"130103",
				name:"桥东区"
			}, {
				organization:[],
				orgNo:"130104",
				name:"桥西区"
			}, {
				organization:[],
				orgNo:"130105",
				name:"新华区"
			}, {
				organization:[],
				orgNo:"130107",
				name:"井陉矿区"
			}, {
				organization:[],
				orgNo:"130108",
				name:"裕华区"
			}, {
				organization:[],
				orgNo:"130121",
				name:"井陉县"
			}, {
				organization:[],
				orgNo:"130123",
				name:"正定县"
			}, {
				organization:[],
				orgNo:"130124",
				name:"栾城县"
			}, {
				organization:[],
				orgNo:"130125",
				name:"行唐县"
			}, {
				organization:[],
				orgNo:"130126",
				name:"灵寿县"
			}, {
				organization:[],
				orgNo:"130127",
				name:"高邑县"
			}, {
				organization:[],
				orgNo:"130128",
				name:"深泽县"
			}, {
				organization:[],
				orgNo:"130129",
				name:"赞皇县"
			}, {
				organization:[],
				orgNo:"130130",
				name:"无极县"
			}, {
				organization:[],
				orgNo:"130131",
				name:"平山县"
			}, {
				organization:[],
				orgNo:"130132",
				name:"元氏县"
			}, {
				organization:[],
				orgNo:"130133",
				name:"赵县"
			}, {
				organization:[],
				orgNo:"130181",
				name:"辛集市"
			}, {
				organization:[],
				orgNo:"130182",
				name:"藁城市"
			}, {
				organization:[],
				orgNo:"130183",
				name:"晋州市"
			}, {
				organization:[],
				orgNo:"130184",
				name:"新乐市"
			}, {
				organization:[],
				orgNo:"130185",
				name:"鹿泉市"
			} ],
			orgNo:"1301",
			name:"石家庄市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"130202",
				name:"路南区"
			}, {
				organization:[],
				orgNo:"130203",
				name:"路北区"
			}, {
				organization:[],
				orgNo:"130204",
				name:"古冶区"
			}, {
				organization:[],
				orgNo:"130205",
				name:"开平区"
			}, {
				organization:[],
				orgNo:"130207",
				name:"丰南区"
			}, {
				organization:[],
				orgNo:"130208",
				name:"丰润区"
			}, {
				organization:[],
				orgNo:"130223",
				name:"滦县"
			}, {
				organization:[],
				orgNo:"130224",
				name:"滦南县"
			}, {
				organization:[],
				orgNo:"130225",
				name:"乐亭县"
			}, {
				organization:[],
				orgNo:"130227",
				name:"迁西县"
			}, {
				organization:[],
				orgNo:"130229",
				name:"玉田县"
			}, {
				organization:[],
				orgNo:"130230",
				name:"唐海县"
			}, {
				organization:[],
				orgNo:"130281",
				name:"遵化市"
			}, {
				organization:[],
				orgNo:"130283",
				name:"迁安市"
			} ],
			orgNo:"1302",
			name:"唐山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"130302",
				name:"海港区"
			}, {
				organization:[],
				orgNo:"130303",
				name:"山海关区"
			}, {
				organization:[],
				orgNo:"130304",
				name:"北戴河区"
			}, {
				organization:[],
				orgNo:"130305",
				name:"开发区"
			}, {
				organization:[],
				orgNo:"130321",
				name:"青龙满族自治县"
			}, {
				organization:[],
				orgNo:"130322",
				name:"昌黎县"
			}, {
				organization:[],
				orgNo:"130323",
				name:"抚宁县"
			}, {
				organization:[],
				orgNo:"130324",
				name:"卢龙县"
			} ],
			orgNo:"1303",
			name:"秦皇岛市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"130402",
				name:"邯山区"
			}, {
				organization:[],
				orgNo:"130403",
				name:"丛台区"
			}, {
				organization:[],
				orgNo:"130404",
				name:"复兴区"
			}, {
				organization:[],
				orgNo:"130406",
				name:"峰峰矿区"
			}, {
				organization:[],
				orgNo:"130421",
				name:"邯郸县"
			}, {
				organization:[],
				orgNo:"130423",
				name:"临漳县"
			}, {
				organization:[],
				orgNo:"130424",
				name:"成安县"
			}, {
				organization:[],
				orgNo:"130425",
				name:"大名县"
			}, {
				organization:[],
				orgNo:"130426",
				name:"涉县"
			}, {
				organization:[],
				orgNo:"130427",
				name:"磁县"
			}, {
				organization:[],
				orgNo:"130428",
				name:"肥乡县"
			}, {
				organization:[],
				orgNo:"130429",
				name:"永年县"
			}, {
				organization:[],
				orgNo:"130430",
				name:"邱县"
			}, {
				organization:[],
				orgNo:"130431",
				name:"鸡泽县"
			}, {
				organization:[],
				orgNo:"130432",
				name:"广平县"
			}, {
				organization:[],
				orgNo:"130433",
				name:"馆陶县"
			}, {
				organization:[],
				orgNo:"130434",
				name:"魏县"
			}, {
				organization:[],
				orgNo:"130435",
				name:"曲周县"
			}, {
				organization:[],
				orgNo:"130481",
				name:"武安市"
			} ],
			orgNo:"1304",
			name:"邯郸市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"130502",
				name:"桥东区"
			}, {
				organization:[],
				orgNo:"130503",
				name:"桥西区"
			}, {
				organization:[],
				orgNo:"130521",
				name:"邢台县"
			}, {
				organization:[],
				orgNo:"130522",
				name:"临城县"
			}, {
				organization:[],
				orgNo:"130523",
				name:"内丘县"
			}, {
				organization:[],
				orgNo:"130524",
				name:"柏乡县"
			}, {
				organization:[],
				orgNo:"130525",
				name:"隆尧县"
			}, {
				organization:[],
				orgNo:"130526",
				name:"任县"
			}, {
				organization:[],
				orgNo:"130527",
				name:"南和县"
			}, {
				organization:[],
				orgNo:"130528",
				name:"宁晋县"
			}, {
				organization:[],
				orgNo:"130529",
				name:"巨鹿县"
			}, {
				organization:[],
				orgNo:"130530",
				name:"新河县"
			}, {
				organization:[],
				orgNo:"130531",
				name:"广宗县"
			}, {
				organization:[],
				orgNo:"130532",
				name:"平乡县"
			}, {
				organization:[],
				orgNo:"130533",
				name:"威县"
			}, {
				organization:[],
				orgNo:"130534",
				name:"清河县"
			}, {
				organization:[],
				orgNo:"130535",
				name:"临西县"
			}, {
				organization:[],
				orgNo:"130581",
				name:"南宫市"
			}, {
				organization:[],
				orgNo:"130582",
				name:"沙河市"
			} ],
			orgNo:"1305",
			name:"邢台市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"130602",
				name:"新市区"
			}, {
				organization:[],
				orgNo:"130603",
				name:"北市区"
			}, {
				organization:[],
				orgNo:"130604",
				name:"南市区"
			}, {
				organization:[],
				orgNo:"130621",
				name:"满城县"
			}, {
				organization:[],
				orgNo:"130622",
				name:"清苑县"
			}, {
				organization:[],
				orgNo:"130623",
				name:"涞水县"
			}, {
				organization:[],
				orgNo:"130624",
				name:"阜平县"
			}, {
				organization:[],
				orgNo:"130625",
				name:"徐水县"
			}, {
				organization:[],
				orgNo:"130626",
				name:"定兴县"
			}, {
				organization:[],
				orgNo:"130627",
				name:"唐县"
			}, {
				organization:[],
				orgNo:"130628",
				name:"高阳县"
			}, {
				organization:[],
				orgNo:"130629",
				name:"容城县"
			}, {
				organization:[],
				orgNo:"130630",
				name:"涞源县"
			}, {
				organization:[],
				orgNo:"130631",
				name:"望都县"
			}, {
				organization:[],
				orgNo:"130632",
				name:"安新县"
			}, {
				organization:[],
				orgNo:"130633",
				name:"易县"
			}, {
				organization:[],
				orgNo:"130634",
				name:"曲阳县"
			}, {
				organization:[],
				orgNo:"130635",
				name:"蠡县"
			}, {
				organization:[],
				orgNo:"130636",
				name:"顺平县"
			}, {
				organization:[],
				orgNo:"130637",
				name:"博野县"
			}, {
				organization:[],
				orgNo:"130638",
				name:"雄县"
			}, {
				organization:[],
				orgNo:"130681",
				name:"涿州市"
			}, {
				organization:[],
				orgNo:"130682",
				name:"定州市"
			}, {
				organization:[],
				orgNo:"130683",
				name:"安国市"
			}, {
				organization:[],
				orgNo:"130684",
				name:"高碑店市"
			} ],
			orgNo:"1306",
			name:"保定市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"130702",
				name:"桥东区"
			}, {
				organization:[],
				orgNo:"130703",
				name:"桥西区"
			}, {
				organization:[],
				orgNo:"130705",
				name:"宣化区"
			}, {
				organization:[],
				orgNo:"130706",
				name:"下花园区"
			}, {
				organization:[],
				orgNo:"130721",
				name:"宣化县"
			}, {
				organization:[],
				orgNo:"130722",
				name:"张北县"
			}, {
				organization:[],
				orgNo:"130723",
				name:"康保县"
			}, {
				organization:[],
				orgNo:"130724",
				name:"沽源县"
			}, {
				organization:[],
				orgNo:"130725",
				name:"尚义县"
			}, {
				organization:[],
				orgNo:"130726",
				name:"蔚县"
			}, {
				organization:[],
				orgNo:"130727",
				name:"阳原县"
			}, {
				organization:[],
				orgNo:"130728",
				name:"怀安县"
			}, {
				organization:[],
				orgNo:"130729",
				name:"万全县"
			}, {
				organization:[],
				orgNo:"130730",
				name:"怀来县"
			}, {
				organization:[],
				orgNo:"130731",
				name:"涿鹿县"
			}, {
				organization:[],
				orgNo:"130732",
				name:"赤城县"
			}, {
				organization:[],
				orgNo:"130733",
				name:"崇礼县"
			} ],
			orgNo:"1307",
			name:"张家口市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"130802",
				name:"双桥区"
			}, {
				organization:[],
				orgNo:"130803",
				name:"双滦区"
			}, {
				organization:[],
				orgNo:"130804",
				name:"鹰手营子矿区"
			}, {
				organization:[],
				orgNo:"130821",
				name:"承德县"
			}, {
				organization:[],
				orgNo:"130822",
				name:"兴隆县"
			}, {
				organization:[],
				orgNo:"130823",
				name:"平泉县"
			}, {
				organization:[],
				orgNo:"130824",
				name:"滦平县"
			}, {
				organization:[],
				orgNo:"130825",
				name:"隆化县"
			}, {
				organization:[],
				orgNo:"130826",
				name:"丰宁满族自治县"
			}, {
				organization:[],
				orgNo:"130827",
				name:"宽城满族自治县"
			}, {
				organization:[],
				orgNo:"130828",
				name:"围场满族蒙古族自治县"
			} ],
			orgNo:"1308",
			name:"承德市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"130902",
				name:"新华区"
			}, {
				organization:[],
				orgNo:"130903",
				name:"运河区"
			}, {
				organization:[],
				orgNo:"130921",
				name:"沧县"
			}, {
				organization:[],
				orgNo:"130922",
				name:"青县"
			}, {
				organization:[],
				orgNo:"130923",
				name:"东光县"
			}, {
				organization:[],
				orgNo:"130924",
				name:"海兴县"
			}, {
				organization:[],
				orgNo:"130925",
				name:"盐山县"
			}, {
				organization:[],
				orgNo:"130926",
				name:"肃宁县"
			}, {
				organization:[],
				orgNo:"130927",
				name:"南皮县"
			}, {
				organization:[],
				orgNo:"130928",
				name:"吴桥县"
			}, {
				organization:[],
				orgNo:"130929",
				name:"献县"
			}, {
				organization:[],
				orgNo:"130930",
				name:"孟村回族自治县"
			}, {
				organization:[],
				orgNo:"130981",
				name:"泊头市"
			}, {
				organization:[],
				orgNo:"130982",
				name:"任丘市"
			}, {
				organization:[],
				orgNo:"130983",
				name:"黄骅市"
			}, {
				organization:[],
				orgNo:"130984",
				name:"河间市"
			} ],
			orgNo:"1309",
			name:"沧州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"131002",
				name:"安次区"
			}, {
				organization:[],
				orgNo:"131003",
				name:"广阳区"
			}, {
				organization:[],
				orgNo:"131022",
				name:"固安县"
			}, {
				organization:[],
				orgNo:"131023",
				name:"永清县"
			}, {
				organization:[],
				orgNo:"131024",
				name:"香河县"
			}, {
				organization:[],
				orgNo:"131025",
				name:"大城县"
			}, {
				organization:[],
				orgNo:"131026",
				name:"文安县"
			}, {
				organization:[],
				orgNo:"131028",
				name:"大厂回族自治县"
			}, {
				organization:[],
				orgNo:"131081",
				name:"霸州市"
			}, {
				organization:[],
				orgNo:"131082",
				name:"三河市"
			} ],
			orgNo:"1310",
			name:"廊坊市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"131102",
				name:"桃城区"
			}, {
				organization:[],
				orgNo:"131121",
				name:"枣强县"
			}, {
				organization:[],
				orgNo:"131122",
				name:"武邑县"
			}, {
				organization:[],
				orgNo:"131123",
				name:"武强县"
			}, {
				organization:[],
				orgNo:"131124",
				name:"饶阳县"
			}, {
				organization:[],
				orgNo:"131125",
				name:"安平县"
			}, {
				organization:[],
				orgNo:"131126",
				name:"故城县"
			}, {
				organization:[],
				orgNo:"131127",
				name:"景县"
			}, {
				organization:[],
				orgNo:"131128",
				name:"阜城县"
			}, {
				organization:[],
				orgNo:"131181",
				name:"冀州市"
			}, {
				organization:[],
				orgNo:"131182",
				name:"深州市"
			} ],
			orgNo:"1311",
			name:"衡水市"
		} ],
		orgNo:"13",
		name:"河北"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"140105",
				name:"小店区"
			}, {
				organization:[],
				orgNo:"140106",
				name:"迎泽区"
			}, {
				organization:[],
				orgNo:"140107",
				name:"杏花岭区"
			}, {
				organization:[],
				orgNo:"140108",
				name:"尖草坪区"
			}, {
				organization:[],
				orgNo:"140109",
				name:"万柏林区"
			}, {
				organization:[],
				orgNo:"140110",
				name:"晋源区"
			}, {
				organization:[],
				orgNo:"140121",
				name:"清徐县"
			}, {
				organization:[],
				orgNo:"140122",
				name:"阳曲县"
			}, {
				organization:[],
				orgNo:"140123",
				name:"娄烦县"
			}, {
				organization:[],
				orgNo:"140181",
				name:"古交市"
			} ],
			orgNo:"1401",
			name:"太原市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"140202",
				name:"城区"
			}, {
				organization:[],
				orgNo:"140203",
				name:"矿区"
			}, {
				organization:[],
				orgNo:"140211",
				name:"南郊区"
			}, {
				organization:[],
				orgNo:"140212",
				name:"新荣区"
			}, {
				organization:[],
				orgNo:"140221",
				name:"阳高县"
			}, {
				organization:[],
				orgNo:"140222",
				name:"天镇县"
			}, {
				organization:[],
				orgNo:"140223",
				name:"广灵县"
			}, {
				organization:[],
				orgNo:"140224",
				name:"灵丘县"
			}, {
				organization:[],
				orgNo:"140225",
				name:"浑源县"
			}, {
				organization:[],
				orgNo:"140226",
				name:"左云县"
			}, {
				organization:[],
				orgNo:"140227",
				name:"大同县"
			} ],
			orgNo:"1402",
			name:"大同市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"140302",
				name:"城区"
			}, {
				organization:[],
				orgNo:"140303",
				name:"矿区"
			}, {
				organization:[],
				orgNo:"140311",
				name:"郊区"
			}, {
				organization:[],
				orgNo:"140321",
				name:"平定县"
			}, {
				organization:[],
				orgNo:"140322",
				name:"盂县"
			} ],
			orgNo:"1403",
			name:"阳泉市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"140402",
				name:"城区"
			}, {
				organization:[],
				orgNo:"140411",
				name:"郊区"
			}, {
				organization:[],
				orgNo:"140421",
				name:"长治县"
			}, {
				organization:[],
				orgNo:"140423",
				name:"襄垣县"
			}, {
				organization:[],
				orgNo:"140424",
				name:"屯留县"
			}, {
				organization:[],
				orgNo:"140425",
				name:"平顺县"
			}, {
				organization:[],
				orgNo:"140426",
				name:"黎城县"
			}, {
				organization:[],
				orgNo:"140427",
				name:"壶关县"
			}, {
				organization:[],
				orgNo:"140428",
				name:"长子县"
			}, {
				organization:[],
				orgNo:"140429",
				name:"武乡县"
			}, {
				organization:[],
				orgNo:"140430",
				name:"沁县"
			}, {
				organization:[],
				orgNo:"140431",
				name:"沁源县"
			}, {
				organization:[],
				orgNo:"140481",
				name:"潞城市"
			} ],
			orgNo:"1404",
			name:"长治市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"140502",
				name:"城区"
			}, {
				organization:[],
				orgNo:"140521",
				name:"沁水县"
			}, {
				organization:[],
				orgNo:"140522",
				name:"阳城县"
			}, {
				organization:[],
				orgNo:"140524",
				name:"陵川县"
			}, {
				organization:[],
				orgNo:"140525",
				name:"泽州县"
			}, {
				organization:[],
				orgNo:"140581",
				name:"高平市"
			} ],
			orgNo:"1405",
			name:"晋城市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"140602",
				name:"朔城区"
			}, {
				organization:[],
				orgNo:"140603",
				name:"平鲁区"
			}, {
				organization:[],
				orgNo:"140621",
				name:"山阴县"
			}, {
				organization:[],
				orgNo:"140622",
				name:"应县"
			}, {
				organization:[],
				orgNo:"140623",
				name:"右玉县"
			}, {
				organization:[],
				orgNo:"140624",
				name:"怀仁县"
			} ],
			orgNo:"1406",
			name:"朔州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"140702",
				name:"榆次区"
			}, {
				organization:[],
				orgNo:"140721",
				name:"榆社县"
			}, {
				organization:[],
				orgNo:"140722",
				name:"左权县"
			}, {
				organization:[],
				orgNo:"140723",
				name:"和顺县"
			}, {
				organization:[],
				orgNo:"140724",
				name:"昔阳县"
			}, {
				organization:[],
				orgNo:"140725",
				name:"寿阳县"
			}, {
				organization:[],
				orgNo:"140726",
				name:"太谷县"
			}, {
				organization:[],
				orgNo:"140727",
				name:"祁县"
			}, {
				organization:[],
				orgNo:"140728",
				name:"平遥县"
			}, {
				organization:[],
				orgNo:"140729",
				name:"灵石县"
			}, {
				organization:[],
				orgNo:"140781",
				name:"介休市"
			} ],
			orgNo:"1407",
			name:"晋中市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"140802",
				name:"盐湖区"
			}, {
				organization:[],
				orgNo:"140821",
				name:"临猗县"
			}, {
				organization:[],
				orgNo:"140822",
				name:"万荣县"
			}, {
				organization:[],
				orgNo:"140823",
				name:"闻喜县"
			}, {
				organization:[],
				orgNo:"140824",
				name:"稷山县"
			}, {
				organization:[],
				orgNo:"140825",
				name:"新绛县"
			}, {
				organization:[],
				orgNo:"140826",
				name:"绛县"
			}, {
				organization:[],
				orgNo:"140827",
				name:"垣曲县"
			}, {
				organization:[],
				orgNo:"140828",
				name:"夏县"
			}, {
				organization:[],
				orgNo:"140829",
				name:"平陆县"
			}, {
				organization:[],
				orgNo:"140830",
				name:"芮城县"
			}, {
				organization:[],
				orgNo:"140881",
				name:"永济市"
			}, {
				organization:[],
				orgNo:"140882",
				name:"河津市"
			} ],
			orgNo:"1408",
			name:"运城市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"140902",
				name:"忻府区"
			}, {
				organization:[],
				orgNo:"140921",
				name:"定襄县"
			}, {
				organization:[],
				orgNo:"140922",
				name:"五台县"
			}, {
				organization:[],
				orgNo:"140923",
				name:"代县"
			}, {
				organization:[],
				orgNo:"140924",
				name:"繁峙县"
			}, {
				organization:[],
				orgNo:"140925",
				name:"宁武县"
			}, {
				organization:[],
				orgNo:"140926",
				name:"静乐县"
			}, {
				organization:[],
				orgNo:"140927",
				name:"神池县"
			}, {
				organization:[],
				orgNo:"140928",
				name:"五寨县"
			}, {
				organization:[],
				orgNo:"140929",
				name:"岢岚县"
			}, {
				organization:[],
				orgNo:"140930",
				name:"河曲县"
			}, {
				organization:[],
				orgNo:"140931",
				name:"保德县"
			}, {
				organization:[],
				orgNo:"140932",
				name:"偏关县"
			}, {
				organization:[],
				orgNo:"140981",
				name:"原平市"
			} ],
			orgNo:"1409",
			name:"忻州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"141002",
				name:"尧都区"
			}, {
				organization:[],
				orgNo:"141021",
				name:"曲沃县"
			}, {
				organization:[],
				orgNo:"141022",
				name:"翼城县"
			}, {
				organization:[],
				orgNo:"141023",
				name:"襄汾县"
			}, {
				organization:[],
				orgNo:"141024",
				name:"洪洞县"
			}, {
				organization:[],
				orgNo:"141025",
				name:"古县"
			}, {
				organization:[],
				orgNo:"141026",
				name:"安泽县"
			}, {
				organization:[],
				orgNo:"141027",
				name:"浮山县"
			}, {
				organization:[],
				orgNo:"141028",
				name:"吉县"
			}, {
				organization:[],
				orgNo:"141029",
				name:"乡宁县"
			}, {
				organization:[],
				orgNo:"141030",
				name:"大宁县"
			}, {
				organization:[],
				orgNo:"141031",
				name:"隰县"
			}, {
				organization:[],
				orgNo:"141032",
				name:"永和县"
			}, {
				organization:[],
				orgNo:"141033",
				name:"蒲县"
			}, {
				organization:[],
				orgNo:"141034",
				name:"汾西县"
			}, {
				organization:[],
				orgNo:"141081",
				name:"侯马市"
			}, {
				organization:[],
				orgNo:"141082",
				name:"霍州市"
			} ],
			orgNo:"1410",
			name:"临汾市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"141102",
				name:"离石区"
			}, {
				organization:[],
				orgNo:"141121",
				name:"文水县"
			}, {
				organization:[],
				orgNo:"141122",
				name:"交城县"
			}, {
				organization:[],
				orgNo:"141123",
				name:"兴县"
			}, {
				organization:[],
				orgNo:"141124",
				name:"临县"
			}, {
				organization:[],
				orgNo:"141125",
				name:"柳林县"
			}, {
				organization:[],
				orgNo:"141126",
				name:"石楼县"
			}, {
				organization:[],
				orgNo:"141127",
				name:"岚县"
			}, {
				organization:[],
				orgNo:"141128",
				name:"方山县"
			}, {
				organization:[],
				orgNo:"141129",
				name:"中阳县"
			}, {
				organization:[],
				orgNo:"141130",
				name:"交口县"
			}, {
				organization:[],
				orgNo:"141181",
				name:"孝义市"
			}, {
				organization:[],
				orgNo:"141182",
				name:"汾阳市"
			} ],
			orgNo:"1411",
			name:"吕梁市"
		} ],
		orgNo:"14",
		name:"山西"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"150102",
				name:"新城区"
			}, {
				organization:[],
				orgNo:"150103",
				name:"回民区"
			}, {
				organization:[],
				orgNo:"150104",
				name:"玉泉区"
			}, {
				organization:[],
				orgNo:"150105",
				name:"赛罕区"
			}, {
				organization:[],
				orgNo:"150121",
				name:"土默特左旗"
			}, {
				organization:[],
				orgNo:"150122",
				name:"托克托县"
			}, {
				organization:[],
				orgNo:"150123",
				name:"和林格尔县"
			}, {
				organization:[],
				orgNo:"150124",
				name:"清水河县"
			}, {
				organization:[],
				orgNo:"150125",
				name:"武川县"
			} ],
			orgNo:"1501",
			name:"呼和浩特市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"150202",
				name:"东河区"
			}, {
				organization:[],
				orgNo:"150203",
				name:"昆都仑区"
			}, {
				organization:[],
				orgNo:"150204",
				name:"青山区"
			}, {
				organization:[],
				orgNo:"150205",
				name:"石拐区"
			}, {
				organization:[],
				orgNo:"150206",
				name:"白云矿区"
			}, {
				organization:[],
				orgNo:"150207",
				name:"九原区"
			}, {
				organization:[],
				orgNo:"150221",
				name:"土默特右旗"
			}, {
				organization:[],
				orgNo:"150222",
				name:"固阳县"
			}, {
				organization:[],
				orgNo:"150223",
				name:"达尔罕茂明安联合旗"
			} ],
			orgNo:"1502",
			name:"包头市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"150302",
				name:"海勃湾区"
			}, {
				organization:[],
				orgNo:"150303",
				name:"海南区"
			}, {
				organization:[],
				orgNo:"150304",
				name:"乌达区"
			} ],
			orgNo:"1503",
			name:"乌海市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"150402",
				name:"红山区"
			}, {
				organization:[],
				orgNo:"150403",
				name:"元宝山区"
			}, {
				organization:[],
				orgNo:"150404",
				name:"松山区"
			}, {
				organization:[],
				orgNo:"150421",
				name:"阿鲁科尔沁旗"
			}, {
				organization:[],
				orgNo:"150422",
				name:"巴林左旗"
			}, {
				organization:[],
				orgNo:"150423",
				name:"巴林右旗"
			}, {
				organization:[],
				orgNo:"150424",
				name:"林西县"
			}, {
				organization:[],
				orgNo:"150425",
				name:"克什克腾旗"
			}, {
				organization:[],
				orgNo:"150426",
				name:"翁牛特旗"
			}, {
				organization:[],
				orgNo:"150428",
				name:"喀喇沁旗"
			}, {
				organization:[],
				orgNo:"150429",
				name:"宁城县"
			}, {
				organization:[],
				orgNo:"150430",
				name:"敖汉旗"
			} ],
			orgNo:"1504",
			name:"赤峰市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"150502",
				name:"科尔沁区"
			}, {
				organization:[],
				orgNo:"150521",
				name:"科尔沁左翼中旗"
			}, {
				organization:[],
				orgNo:"150522",
				name:"科尔沁左翼后旗"
			}, {
				organization:[],
				orgNo:"150523",
				name:"开鲁县"
			}, {
				organization:[],
				orgNo:"150524",
				name:"库伦旗"
			}, {
				organization:[],
				orgNo:"150525",
				name:"奈曼旗"
			}, {
				organization:[],
				orgNo:"150526",
				name:"扎鲁特旗"
			}, {
				organization:[],
				orgNo:"150581",
				name:"霍林郭勒市"
			} ],
			orgNo:"1505",
			name:"通辽市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"150602",
				name:"东胜区"
			}, {
				organization:[],
				orgNo:"150621",
				name:"达拉特旗"
			}, {
				organization:[],
				orgNo:"150622",
				name:"准格尔旗"
			}, {
				organization:[],
				orgNo:"150623",
				name:"鄂托克前旗"
			}, {
				organization:[],
				orgNo:"150624",
				name:"鄂托克旗"
			}, {
				organization:[],
				orgNo:"150625",
				name:"杭锦旗"
			}, {
				organization:[],
				orgNo:"150626",
				name:"乌审旗"
			}, {
				organization:[],
				orgNo:"150627",
				name:"伊金霍洛旗"
			} ],
			orgNo:"1506",
			name:"鄂尔多斯市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"150702",
				name:"海拉尔区"
			}, {
				organization:[],
				orgNo:"150721",
				name:"阿荣旗"
			}, {
				organization:[],
				orgNo:"150722",
				name:"莫力达瓦达斡尔族自治旗"
			}, {
				organization:[],
				orgNo:"150723",
				name:"鄂伦春自治旗"
			}, {
				organization:[],
				orgNo:"150724",
				name:"鄂温克族自治旗"
			}, {
				organization:[],
				orgNo:"150725",
				name:"陈巴尔虎旗"
			}, {
				organization:[],
				orgNo:"150726",
				name:"新巴尔虎左旗"
			}, {
				organization:[],
				orgNo:"150727",
				name:"新巴尔虎右旗"
			}, {
				organization:[],
				orgNo:"150781",
				name:"满洲里市"
			}, {
				organization:[],
				orgNo:"150782",
				name:"牙克石市"
			}, {
				organization:[],
				orgNo:"150783",
				name:"扎兰屯市"
			}, {
				organization:[],
				orgNo:"150784",
				name:"额尔古纳市"
			}, {
				organization:[],
				orgNo:"150785",
				name:"根河市"
			} ],
			orgNo:"1507",
			name:"呼伦贝尔市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"150802",
				name:"临河区"
			}, {
				organization:[],
				orgNo:"150821",
				name:"五原县"
			}, {
				organization:[],
				orgNo:"150822",
				name:"磴口县"
			}, {
				organization:[],
				orgNo:"150823",
				name:"乌拉特前旗"
			}, {
				organization:[],
				orgNo:"150824",
				name:"乌拉特中旗"
			}, {
				organization:[],
				orgNo:"150825",
				name:"乌拉特后旗"
			}, {
				organization:[],
				orgNo:"150826",
				name:"杭锦后旗"
			} ],
			orgNo:"1508",
			name:"巴彦淖尔市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"150902",
				name:"集宁区"
			}, {
				organization:[],
				orgNo:"150921",
				name:"卓资县"
			}, {
				organization:[],
				orgNo:"150922",
				name:"化德县"
			}, {
				organization:[],
				orgNo:"150923",
				name:"商都县"
			}, {
				organization:[],
				orgNo:"150924",
				name:"兴和县"
			}, {
				organization:[],
				orgNo:"150925",
				name:"凉城县"
			}, {
				organization:[],
				orgNo:"150926",
				name:"察哈尔右翼前旗"
			}, {
				organization:[],
				orgNo:"150927",
				name:"察哈尔右翼中旗"
			}, {
				organization:[],
				orgNo:"150928",
				name:"察哈尔右翼后旗"
			}, {
				organization:[],
				orgNo:"150929",
				name:"四子王旗"
			}, {
				organization:[],
				orgNo:"150981",
				name:"丰镇市"
			} ],
			orgNo:"1509",
			name:"乌兰察布市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"152201",
				name:"乌兰浩特市"
			}, {
				organization:[],
				orgNo:"152202",
				name:"阿尔山市"
			}, {
				organization:[],
				orgNo:"152221",
				name:"科尔沁右翼前旗"
			}, {
				organization:[],
				orgNo:"152222",
				name:"科尔沁右翼中旗"
			}, {
				organization:[],
				orgNo:"152223",
				name:"扎赉特旗"
			}, {
				organization:[],
				orgNo:"152224",
				name:"突泉县"
			} ],
			orgNo:"1522",
			name:"兴安盟"
		}, {
			organization:[ {
				organization:[],
				orgNo:"152501",
				name:"二连浩特市"
			}, {
				organization:[],
				orgNo:"152502",
				name:"锡林浩特市"
			}, {
				organization:[],
				orgNo:"152522",
				name:"阿巴嘎旗"
			}, {
				organization:[],
				orgNo:"152523",
				name:"苏尼特左旗"
			}, {
				organization:[],
				orgNo:"152524",
				name:"苏尼特右旗"
			}, {
				organization:[],
				orgNo:"152525",
				name:"东乌珠穆沁旗"
			}, {
				organization:[],
				orgNo:"152526",
				name:"西乌珠穆沁旗"
			}, {
				organization:[],
				orgNo:"152527",
				name:"太仆寺旗"
			}, {
				organization:[],
				orgNo:"152528",
				name:"镶黄旗"
			}, {
				organization:[],
				orgNo:"152529",
				name:"正镶白旗"
			}, {
				organization:[],
				orgNo:"152530",
				name:"正蓝旗"
			}, {
				organization:[],
				orgNo:"152531",
				name:"多伦县"
			} ],
			orgNo:"1525",
			name:"锡林郭勒盟"
		}, {
			organization:[ {
				organization:[],
				orgNo:"152921",
				name:"阿拉善左旗"
			}, {
				organization:[],
				orgNo:"152922",
				name:"阿拉善右旗"
			}, {
				organization:[],
				orgNo:"152923",
				name:"额济纳旗"
			} ],
			orgNo:"1529",
			name:"阿拉善盟"
		} ],
		orgNo:"15",
		name:"内蒙古"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"210102",
				name:"和平区"
			}, {
				organization:[],
				orgNo:"210103",
				name:"沈河区"
			}, {
				organization:[],
				orgNo:"210104",
				name:"大东区"
			}, {
				organization:[],
				orgNo:"210105",
				name:"皇姑区"
			}, {
				organization:[],
				orgNo:"210106",
				name:"铁西区"
			}, {
				organization:[],
				orgNo:"210107",
				name:"浑南新区"
			}, {
				organization:[],
				orgNo:"210111",
				name:"苏家屯区"
			}, {
				organization:[],
				orgNo:"210112",
				name:"东陵区"
			}, {
				organization:[],
				orgNo:"210113",
				name:"沈北新区"
			}, {
				organization:[],
				orgNo:"210114",
				name:"于洪区"
			}, {
				organization:[],
				orgNo:"210122",
				name:"辽中县"
			}, {
				organization:[],
				orgNo:"210123",
				name:"康平县"
			}, {
				organization:[],
				orgNo:"210124",
				name:"法库县"
			}, {
				organization:[],
				orgNo:"210181",
				name:"新民市"
			} ],
			orgNo:"2101",
			name:"沈阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"210202",
				name:"中山区"
			}, {
				organization:[],
				orgNo:"210203",
				name:"西岗区"
			}, {
				organization:[],
				orgNo:"210204",
				name:"沙河口区"
			}, {
				organization:[],
				orgNo:"210211",
				name:"甘井子区"
			}, {
				organization:[],
				orgNo:"210212",
				name:"旅顺口区"
			}, {
				organization:[],
				orgNo:"210213",
				name:"金州区"
			}, {
				organization:[],
				orgNo:"210224",
				name:"长海县"
			}, {
				organization:[],
				orgNo:"210281",
				name:"瓦房店市"
			}, {
				organization:[],
				orgNo:"210282",
				name:"普兰店市"
			}, {
				organization:[],
				orgNo:"210283",
				name:"庄河市"
			} ],
			orgNo:"2102",
			name:"大连市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"210302",
				name:"铁东区"
			}, {
				organization:[],
				orgNo:"210303",
				name:"铁西区"
			}, {
				organization:[],
				orgNo:"210304",
				name:"立山区"
			}, {
				organization:[],
				orgNo:"210311",
				name:"千山区"
			}, {
				organization:[],
				orgNo:"210321",
				name:"台安县"
			}, {
				organization:[],
				orgNo:"210323",
				name:"岫岩满族自治县"
			}, {
				organization:[],
				orgNo:"210381",
				name:"海城市"
			} ],
			orgNo:"2103",
			name:"鞍山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"210402",
				name:"新抚区"
			}, {
				organization:[],
				orgNo:"210403",
				name:"东洲区"
			}, {
				organization:[],
				orgNo:"210404",
				name:"望花区"
			}, {
				organization:[],
				orgNo:"210411",
				name:"顺城区"
			}, {
				organization:[],
				orgNo:"210421",
				name:"抚顺县"
			}, {
				organization:[],
				orgNo:"210422",
				name:"新宾满族自治县"
			}, {
				organization:[],
				orgNo:"210423",
				name:"清原满族自治县"
			} ],
			orgNo:"2104",
			name:"抚顺市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"210502",
				name:"平山区"
			}, {
				organization:[],
				orgNo:"210503",
				name:"溪湖区"
			}, {
				organization:[],
				orgNo:"210504",
				name:"明山区"
			}, {
				organization:[],
				orgNo:"210505",
				name:"南芬区"
			}, {
				organization:[],
				orgNo:"210521",
				name:"本溪满族自治县"
			}, {
				organization:[],
				orgNo:"210522",
				name:"桓仁满族自治县"
			} ],
			orgNo:"2105",
			name:"本溪市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"210602",
				name:"元宝区"
			}, {
				organization:[],
				orgNo:"210603",
				name:"振兴区"
			}, {
				organization:[],
				orgNo:"210604",
				name:"振安区"
			}, {
				organization:[],
				orgNo:"210624",
				name:"宽甸满族自治县"
			}, {
				organization:[],
				orgNo:"210681",
				name:"东港市"
			}, {
				organization:[],
				orgNo:"210682",
				name:"凤城市"
			} ],
			orgNo:"2106",
			name:"丹东市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"210702",
				name:"古塔区"
			}, {
				organization:[],
				orgNo:"210703",
				name:"凌河区"
			}, {
				organization:[],
				orgNo:"210711",
				name:"太和区"
			}, {
				organization:[],
				orgNo:"210726",
				name:"黑山县"
			}, {
				organization:[],
				orgNo:"210727",
				name:"义县"
			}, {
				organization:[],
				orgNo:"210781",
				name:"凌海市"
			}, {
				organization:[],
				orgNo:"210782",
				name:"北宁市"
			} ],
			orgNo:"2107",
			name:"锦州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"210802",
				name:"站前区"
			}, {
				organization:[],
				orgNo:"210803",
				name:"西市区"
			}, {
				organization:[],
				orgNo:"210804",
				name:"鲅鱼圈区"
			}, {
				organization:[],
				orgNo:"210811",
				name:"老边区"
			}, {
				organization:[],
				orgNo:"210881",
				name:"盖州市"
			}, {
				organization:[],
				orgNo:"210882",
				name:"大石桥市"
			} ],
			orgNo:"2108",
			name:"营口市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"210902",
				name:"海州区"
			}, {
				organization:[],
				orgNo:"210903",
				name:"新邱区"
			}, {
				organization:[],
				orgNo:"210904",
				name:"太平区"
			}, {
				organization:[],
				orgNo:"210905",
				name:"清河门区"
			}, {
				organization:[],
				orgNo:"210911",
				name:"细河区"
			}, {
				organization:[],
				orgNo:"210921",
				name:"阜新蒙古族自治县"
			}, {
				organization:[],
				orgNo:"210922",
				name:"彰武县"
			} ],
			orgNo:"2109",
			name:"阜新市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"211002",
				name:"白塔区"
			}, {
				organization:[],
				orgNo:"211003",
				name:"文圣区"
			}, {
				organization:[],
				orgNo:"211004",
				name:"宏伟区"
			}, {
				organization:[],
				orgNo:"211005",
				name:"弓长岭区"
			}, {
				organization:[],
				orgNo:"211011",
				name:"太子河区"
			}, {
				organization:[],
				orgNo:"211021",
				name:"辽阳县"
			}, {
				organization:[],
				orgNo:"211081",
				name:"灯塔市"
			} ],
			orgNo:"2110",
			name:"辽阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"211102",
				name:"双台子区"
			}, {
				organization:[],
				orgNo:"211103",
				name:"兴隆台区"
			}, {
				organization:[],
				orgNo:"211121",
				name:"大洼县"
			}, {
				organization:[],
				orgNo:"211122",
				name:"盘山县"
			} ],
			orgNo:"2111",
			name:"盘锦市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"211202",
				name:"银州区"
			}, {
				organization:[],
				orgNo:"211204",
				name:"清河区"
			}, {
				organization:[],
				orgNo:"211221",
				name:"铁岭县"
			}, {
				organization:[],
				orgNo:"211223",
				name:"西丰县"
			}, {
				organization:[],
				orgNo:"211224",
				name:"昌图县"
			}, {
				organization:[],
				orgNo:"211281",
				name:"调兵山市"
			}, {
				organization:[],
				orgNo:"211282",
				name:"开原市"
			} ],
			orgNo:"2112",
			name:"铁岭市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"211302",
				name:"双塔区"
			}, {
				organization:[],
				orgNo:"211303",
				name:"龙城区"
			}, {
				organization:[],
				orgNo:"211321",
				name:"朝阳县"
			}, {
				organization:[],
				orgNo:"211322",
				name:"建平县"
			}, {
				organization:[],
				orgNo:"211324",
				name:"喀喇沁左翼蒙古族自治县"
			}, {
				organization:[],
				orgNo:"211381",
				name:"北票市"
			}, {
				organization:[],
				orgNo:"211382",
				name:"凌源市"
			} ],
			orgNo:"2113",
			name:"朝阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"211402",
				name:"连山区"
			}, {
				organization:[],
				orgNo:"211403",
				name:"龙港区"
			}, {
				organization:[],
				orgNo:"211404",
				name:"南票区"
			}, {
				organization:[],
				orgNo:"211421",
				name:"绥中县"
			}, {
				organization:[],
				orgNo:"211422",
				name:"建昌县"
			}, {
				organization:[],
				orgNo:"211481",
				name:"兴城市"
			} ],
			orgNo:"2114",
			name:"葫芦岛市"
		} ],
		orgNo:"21",
		name:"辽宁"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"220102",
				name:"南关区"
			}, {
				organization:[],
				orgNo:"220103",
				name:"宽城区"
			}, {
				organization:[],
				orgNo:"220104",
				name:"朝阳区"
			}, {
				organization:[],
				orgNo:"220105",
				name:"二道区"
			}, {
				organization:[],
				orgNo:"220106",
				name:"绿园区"
			}, {
				organization:[],
				orgNo:"220112",
				name:"双阳区"
			}, {
				organization:[],
				orgNo:"220122",
				name:"农安县"
			}, {
				organization:[],
				orgNo:"220181",
				name:"九台市"
			}, {
				organization:[],
				orgNo:"220182",
				name:"榆树市"
			}, {
				organization:[],
				orgNo:"220183",
				name:"德惠市"
			} ],
			orgNo:"2201",
			name:"长春市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"220202",
				name:"昌邑区"
			}, {
				organization:[],
				orgNo:"220203",
				name:"龙潭区"
			}, {
				organization:[],
				orgNo:"220204",
				name:"船营区"
			}, {
				organization:[],
				orgNo:"220211",
				name:"丰满区"
			}, {
				organization:[],
				orgNo:"220221",
				name:"永吉县"
			}, {
				organization:[],
				orgNo:"220281",
				name:"蛟河市"
			}, {
				organization:[],
				orgNo:"220282",
				name:"桦甸市"
			}, {
				organization:[],
				orgNo:"220283",
				name:"舒兰市"
			}, {
				organization:[],
				orgNo:"220284",
				name:"磐石市"
			} ],
			orgNo:"2202",
			name:"吉林市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"220302",
				name:"铁西区"
			}, {
				organization:[],
				orgNo:"220303",
				name:"铁东区"
			}, {
				organization:[],
				orgNo:"220322",
				name:"梨树县"
			}, {
				organization:[],
				orgNo:"220323",
				name:"伊通满族自治县"
			}, {
				organization:[],
				orgNo:"220381",
				name:"公主岭市"
			}, {
				organization:[],
				orgNo:"220382",
				name:"双辽市"
			} ],
			orgNo:"2203",
			name:"四平市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"220402",
				name:"龙山区"
			}, {
				organization:[],
				orgNo:"220403",
				name:"西安区"
			}, {
				organization:[],
				orgNo:"220421",
				name:"东丰县"
			}, {
				organization:[],
				orgNo:"220422",
				name:"东辽县"
			} ],
			orgNo:"2204",
			name:"辽源市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"220502",
				name:"东昌区"
			}, {
				organization:[],
				orgNo:"220503",
				name:"二道江区"
			}, {
				organization:[],
				orgNo:"220521",
				name:"通化县"
			}, {
				organization:[],
				orgNo:"220523",
				name:"辉南县"
			}, {
				organization:[],
				orgNo:"220524",
				name:"柳河县"
			}, {
				organization:[],
				orgNo:"220581",
				name:"梅河口市"
			}, {
				organization:[],
				orgNo:"220582",
				name:"集安市"
			} ],
			orgNo:"2205",
			name:"通化市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"220602",
				name:"八道江区"
			}, {
				organization:[],
				orgNo:"220604",
				name:"江源区"
			}, {
				organization:[],
				orgNo:"220621",
				name:"抚松县"
			}, {
				organization:[],
				orgNo:"220622",
				name:"靖宇县"
			}, {
				organization:[],
				orgNo:"220623",
				name:"长白朝鲜族自治县"
			}, {
				organization:[],
				orgNo:"220681",
				name:"临江市"
			} ],
			orgNo:"2206",
			name:"白山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"220702",
				name:"宁江区"
			}, {
				organization:[],
				orgNo:"220721",
				name:"前郭尔罗斯蒙古族自治县"
			}, {
				organization:[],
				orgNo:"220722",
				name:"长岭县"
			}, {
				organization:[],
				orgNo:"220723",
				name:"乾安县"
			}, {
				organization:[],
				orgNo:"220724",
				name:"扶余县"
			} ],
			orgNo:"2207",
			name:"松原市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"220802",
				name:"洮北区"
			}, {
				organization:[],
				orgNo:"220821",
				name:"镇赉县"
			}, {
				organization:[],
				orgNo:"220822",
				name:"通榆县"
			}, {
				organization:[],
				orgNo:"220881",
				name:"洮南市"
			}, {
				organization:[],
				orgNo:"220882",
				name:"大安市"
			} ],
			orgNo:"2208",
			name:"白城市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"222401",
				name:"延吉市"
			}, {
				organization:[],
				orgNo:"222402",
				name:"图们市"
			}, {
				organization:[],
				orgNo:"222403",
				name:"敦化市"
			}, {
				organization:[],
				orgNo:"222404",
				name:"珲春市"
			}, {
				organization:[],
				orgNo:"222405",
				name:"龙井市"
			}, {
				organization:[],
				orgNo:"222406",
				name:"和龙市"
			}, {
				organization:[],
				orgNo:"222424",
				name:"汪清县"
			}, {
				organization:[],
				orgNo:"222426",
				name:"安图县"
			} ],
			orgNo:"2224",
			name:"延边朝鲜族自治州"
		} ],
		orgNo:"22",
		name:"吉林"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"230102",
				name:"道里区"
			}, {
				organization:[],
				orgNo:"230103",
				name:"南岗区"
			}, {
				organization:[],
				orgNo:"230104",
				name:"道外区"
			}, {
				organization:[],
				orgNo:"230108",
				name:"平房区"
			}, {
				organization:[],
				orgNo:"230109",
				name:"松北区"
			}, {
				organization:[],
				orgNo:"230110",
				name:"香坊区"
			}, {
				organization:[],
				orgNo:"230111",
				name:"呼兰区"
			}, {
				organization:[],
				orgNo:"230112",
				name:"阿城区"
			}, {
				organization:[],
				orgNo:"230123",
				name:"依兰县"
			}, {
				organization:[],
				orgNo:"230124",
				name:"方正县"
			}, {
				organization:[],
				orgNo:"230125",
				name:"宾县"
			}, {
				organization:[],
				orgNo:"230126",
				name:"巴彦县"
			}, {
				organization:[],
				orgNo:"230127",
				name:"木兰县"
			}, {
				organization:[],
				orgNo:"230128",
				name:"通河县"
			}, {
				organization:[],
				orgNo:"230129",
				name:"延寿县"
			}, {
				organization:[],
				orgNo:"230182",
				name:"双城市"
			}, {
				organization:[],
				orgNo:"230183",
				name:"尚志市"
			}, {
				organization:[],
				orgNo:"230184",
				name:"五常市"
			} ],
			orgNo:"2301",
			name:"哈尔滨市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"230202",
				name:"龙沙区"
			}, {
				organization:[],
				orgNo:"230203",
				name:"建华区"
			}, {
				organization:[],
				orgNo:"230204",
				name:"铁锋区"
			}, {
				organization:[],
				orgNo:"230205",
				name:"昂昂溪区"
			}, {
				organization:[],
				orgNo:"230206",
				name:"富拉尔基区"
			}, {
				organization:[],
				orgNo:"230207",
				name:"碾子山区"
			}, {
				organization:[],
				orgNo:"230208",
				name:"梅里斯达斡尔族区"
			}, {
				organization:[],
				orgNo:"230221",
				name:"龙江县"
			}, {
				organization:[],
				orgNo:"230223",
				name:"依安县"
			}, {
				organization:[],
				orgNo:"230224",
				name:"泰来县"
			}, {
				organization:[],
				orgNo:"230225",
				name:"甘南县"
			}, {
				organization:[],
				orgNo:"230227",
				name:"富裕县"
			}, {
				organization:[],
				orgNo:"230229",
				name:"克山县"
			}, {
				organization:[],
				orgNo:"230230",
				name:"克东县"
			}, {
				organization:[],
				orgNo:"230231",
				name:"拜泉县"
			}, {
				organization:[],
				orgNo:"230281",
				name:"讷河市"
			} ],
			orgNo:"2302",
			name:"齐齐哈尔市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"230302",
				name:"鸡冠区"
			}, {
				organization:[],
				orgNo:"230303",
				name:"恒山区"
			}, {
				organization:[],
				orgNo:"230304",
				name:"滴道区"
			}, {
				organization:[],
				orgNo:"230305",
				name:"梨树区"
			}, {
				organization:[],
				orgNo:"230306",
				name:"城子河区"
			}, {
				organization:[],
				orgNo:"230307",
				name:"麻山区"
			}, {
				organization:[],
				orgNo:"230321",
				name:"鸡东县"
			}, {
				organization:[],
				orgNo:"230381",
				name:"虎林市"
			}, {
				organization:[],
				orgNo:"230382",
				name:"密山市"
			} ],
			orgNo:"2303",
			name:"鸡西市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"230402",
				name:"向阳区"
			}, {
				organization:[],
				orgNo:"230403",
				name:"工农区"
			}, {
				organization:[],
				orgNo:"230404",
				name:"南山区"
			}, {
				organization:[],
				orgNo:"230405",
				name:"兴安区"
			}, {
				organization:[],
				orgNo:"230406",
				name:"东山区"
			}, {
				organization:[],
				orgNo:"230407",
				name:"兴山区"
			}, {
				organization:[],
				orgNo:"230421",
				name:"萝北县"
			}, {
				organization:[],
				orgNo:"230422",
				name:"绥滨县"
			} ],
			orgNo:"2304",
			name:"鹤岗市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"230502",
				name:"尖山区"
			}, {
				organization:[],
				orgNo:"230503",
				name:"岭东区"
			}, {
				organization:[],
				orgNo:"230505",
				name:"四方台区"
			}, {
				organization:[],
				orgNo:"230506",
				name:"宝山区"
			}, {
				organization:[],
				orgNo:"230521",
				name:"集贤县"
			}, {
				organization:[],
				orgNo:"230522",
				name:"友谊县"
			}, {
				organization:[],
				orgNo:"230523",
				name:"宝清县"
			}, {
				organization:[],
				orgNo:"230524",
				name:"饶河县"
			} ],
			orgNo:"2305",
			name:"双鸭山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"230602",
				name:"萨尔图区"
			}, {
				organization:[],
				orgNo:"230603",
				name:"龙凤区"
			}, {
				organization:[],
				orgNo:"230604",
				name:"让胡路区"
			}, {
				organization:[],
				orgNo:"230605",
				name:"红岗区"
			}, {
				organization:[],
				orgNo:"230606",
				name:"大同区"
			}, {
				organization:[],
				orgNo:"230621",
				name:"肇州县"
			}, {
				organization:[],
				orgNo:"230622",
				name:"肇源县"
			}, {
				organization:[],
				orgNo:"230623",
				name:"林甸县"
			}, {
				organization:[],
				orgNo:"230624",
				name:"杜尔伯特蒙古族自治县"
			} ],
			orgNo:"2306",
			name:"大庆市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"230702",
				name:"伊春区"
			}, {
				organization:[],
				orgNo:"230703",
				name:"南岔区"
			}, {
				organization:[],
				orgNo:"230704",
				name:"友好区"
			}, {
				organization:[],
				orgNo:"230705",
				name:"西林区"
			}, {
				organization:[],
				orgNo:"230706",
				name:"翠峦区"
			}, {
				organization:[],
				orgNo:"230707",
				name:"新青区"
			}, {
				organization:[],
				orgNo:"230708",
				name:"美溪区"
			}, {
				organization:[],
				orgNo:"230709",
				name:"金山屯区"
			}, {
				organization:[],
				orgNo:"230710",
				name:"五营区"
			}, {
				organization:[],
				orgNo:"230711",
				name:"乌马河区"
			}, {
				organization:[],
				orgNo:"230712",
				name:"汤旺河区"
			}, {
				organization:[],
				orgNo:"230713",
				name:"带岭区"
			}, {
				organization:[],
				orgNo:"230714",
				name:"乌伊岭区"
			}, {
				organization:[],
				orgNo:"230715",
				name:"红星区"
			}, {
				organization:[],
				orgNo:"230716",
				name:"上甘岭区"
			}, {
				organization:[],
				orgNo:"230722",
				name:"嘉荫县"
			}, {
				organization:[],
				orgNo:"230781",
				name:"铁力市"
			} ],
			orgNo:"2307",
			name:"伊春市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"230803",
				name:"向阳区"
			}, {
				organization:[],
				orgNo:"230804",
				name:"前进区"
			}, {
				organization:[],
				orgNo:"230805",
				name:"东风区"
			}, {
				organization:[],
				orgNo:"230811",
				name:"郊区"
			}, {
				organization:[],
				orgNo:"230822",
				name:"桦南县"
			}, {
				organization:[],
				orgNo:"230826",
				name:"桦川县"
			}, {
				organization:[],
				orgNo:"230828",
				name:"汤原县"
			}, {
				organization:[],
				orgNo:"230833",
				name:"抚远县"
			}, {
				organization:[],
				orgNo:"230881",
				name:"同江市"
			}, {
				organization:[],
				orgNo:"230882",
				name:"富锦市"
			} ],
			orgNo:"2308",
			name:"佳木斯市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"230902",
				name:"新兴区"
			}, {
				organization:[],
				orgNo:"230903",
				name:"桃山区"
			}, {
				organization:[],
				orgNo:"230904",
				name:"茄子河区"
			}, {
				organization:[],
				orgNo:"230921",
				name:"勃利县"
			} ],
			orgNo:"2309",
			name:"七台河市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"231002",
				name:"东安区"
			}, {
				organization:[],
				orgNo:"231003",
				name:"阳明区"
			}, {
				organization:[],
				orgNo:"231004",
				name:"爱民区"
			}, {
				organization:[],
				orgNo:"231005",
				name:"西安区"
			}, {
				organization:[],
				orgNo:"231024",
				name:"东宁县"
			}, {
				organization:[],
				orgNo:"231025",
				name:"林口县"
			}, {
				organization:[],
				orgNo:"231081",
				name:"绥芬河市"
			}, {
				organization:[],
				orgNo:"231083",
				name:"海林市"
			}, {
				organization:[],
				orgNo:"231084",
				name:"宁安市"
			}, {
				organization:[],
				orgNo:"231085",
				name:"穆棱市"
			} ],
			orgNo:"2310",
			name:"牡丹江市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"231102",
				name:"爱辉区"
			}, {
				organization:[],
				orgNo:"231121",
				name:"嫩江县"
			}, {
				organization:[],
				orgNo:"231123",
				name:"逊克县"
			}, {
				organization:[],
				orgNo:"231124",
				name:"孙吴县"
			}, {
				organization:[],
				orgNo:"231181",
				name:"北安市"
			}, {
				organization:[],
				orgNo:"231182",
				name:"五大连池市"
			} ],
			orgNo:"2311",
			name:"黑河市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"231202",
				name:"北林区"
			}, {
				organization:[],
				orgNo:"231221",
				name:"望奎县"
			}, {
				organization:[],
				orgNo:"231222",
				name:"兰西县"
			}, {
				organization:[],
				orgNo:"231223",
				name:"青冈县"
			}, {
				organization:[],
				orgNo:"231224",
				name:"庆安县"
			}, {
				organization:[],
				orgNo:"231225",
				name:"明水县"
			}, {
				organization:[],
				orgNo:"231226",
				name:"绥棱县"
			}, {
				organization:[],
				orgNo:"231281",
				name:"安达市"
			}, {
				organization:[],
				orgNo:"231282",
				name:"肇东市"
			}, {
				organization:[],
				orgNo:"231283",
				name:"海伦市"
			} ],
			orgNo:"2312",
			name:"绥化市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"232701",
				name:"加格达奇区"
			}, {
				organization:[],
				orgNo:"232702",
				name:"松岭区"
			}, {
				organization:[],
				orgNo:"232703",
				name:"新林区"
			}, {
				organization:[],
				orgNo:"232704",
				name:"呼中区"
			}, {
				organization:[],
				orgNo:"232721",
				name:"呼玛县"
			}, {
				organization:[],
				orgNo:"232722",
				name:"塔河县"
			}, {
				organization:[],
				orgNo:"232723",
				name:"漠河县"
			} ],
			orgNo:"2327",
			name:"大兴安岭地区"
		} ],
		orgNo:"23",
		name:"黑龙江"
	}, {
		organization:[ {
			organization:[],
			orgNo:"310101",
			name:"黄浦区"
		}, {
			organization:[],
			orgNo:"310103",
			name:"卢湾区"
		}, {
			organization:[],
			orgNo:"310104",
			name:"徐汇区"
		}, {
			organization:[],
			orgNo:"310105",
			name:"长宁区"
		}, {
			organization:[],
			orgNo:"310106",
			name:"静安区"
		}, {
			organization:[],
			orgNo:"310107",
			name:"普陀区"
		}, {
			organization:[],
			orgNo:"310108",
			name:"闸北区"
		}, {
			organization:[],
			orgNo:"310109",
			name:"虹口区"
		}, {
			organization:[],
			orgNo:"310110",
			name:"杨浦区"
		}, {
			organization:[],
			orgNo:"310112",
			name:"闵行区"
		}, {
			organization:[],
			orgNo:"310113",
			name:"宝山区"
		}, {
			organization:[],
			orgNo:"310114",
			name:"嘉定区"
		}, {
			organization:[],
			orgNo:"310115",
			name:"浦东新区"
		}, {
			organization:[],
			orgNo:"310116",
			name:"金山区"
		}, {
			organization:[],
			orgNo:"310117",
			name:"松江区"
		}, {
			organization:[],
			orgNo:"310118",
			name:"青浦区"
		}, {
			organization:[],
			orgNo:"310119",
			name:"南汇区"
		}, {
			organization:[],
			orgNo:"310120",
			name:"奉贤区"
		}, {
			organization:[],
			orgNo:"310230",
			name:"崇明县"
		} ],
		orgNo:"31",
		name:"上海"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"320102",
				name:"玄武区"
			}, {
				organization:[],
				orgNo:"320103",
				name:"白下区"
			}, {
				organization:[],
				orgNo:"320104",
				name:"秦淮区"
			}, {
				organization:[],
				orgNo:"320105",
				name:"建邺区"
			}, {
				organization:[],
				orgNo:"320106",
				name:"鼓楼区"
			}, {
				organization:[],
				orgNo:"320107",
				name:"下关区"
			}, {
				organization:[],
				orgNo:"320111",
				name:"浦口区"
			}, {
				organization:[],
				orgNo:"320113",
				name:"栖霞区"
			}, {
				organization:[],
				orgNo:"320114",
				name:"雨花台区"
			}, {
				organization:[],
				orgNo:"320115",
				name:"江宁区"
			}, {
				organization:[],
				orgNo:"320116",
				name:"六合区"
			}, {
				organization:[],
				orgNo:"320124",
				name:"溧水县"
			}, {
				organization:[],
				orgNo:"320125",
				name:"高淳县"
			} ],
			orgNo:"3201",
			name:"南京市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"320202",
				name:"崇安区"
			}, {
				organization:[],
				orgNo:"320203",
				name:"南长区"
			}, {
				organization:[],
				orgNo:"320204",
				name:"北塘区"
			}, {
				organization:[],
				orgNo:"320205",
				name:"锡山区"
			}, {
				organization:[],
				orgNo:"320206",
				name:"惠山区"
			}, {
				organization:[],
				orgNo:"320211",
				name:"滨湖区"
			}, {
				organization:[],
				orgNo:"320281",
				name:"江阴市"
			}, {
				organization:[],
				orgNo:"320282",
				name:"宜兴市"
			} ],
			orgNo:"3202",
			name:"无锡市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"320302",
				name:"鼓楼区"
			}, {
				organization:[],
				orgNo:"320303",
				name:"云龙区"
			}, {
				organization:[],
				orgNo:"320304",
				name:"九里区"
			}, {
				organization:[],
				orgNo:"320305",
				name:"贾汪区"
			}, {
				organization:[],
				orgNo:"320311",
				name:"泉山区"
			}, {
				organization:[],
				orgNo:"320321",
				name:"丰县"
			}, {
				organization:[],
				orgNo:"320322",
				name:"沛县"
			}, {
				organization:[],
				orgNo:"320323",
				name:"铜山县"
			}, {
				organization:[],
				orgNo:"320324",
				name:"睢宁县"
			}, {
				organization:[],
				orgNo:"320381",
				name:"新沂市"
			}, {
				organization:[],
				orgNo:"320382",
				name:"邳州市"
			} ],
			orgNo:"3203",
			name:"徐州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"320402",
				name:"天宁区"
			}, {
				organization:[],
				orgNo:"320404",
				name:"钟楼区"
			}, {
				organization:[],
				orgNo:"320405",
				name:"戚墅堰区"
			}, {
				organization:[],
				orgNo:"320411",
				name:"新北区"
			}, {
				organization:[],
				orgNo:"320412",
				name:"武进区"
			}, {
				organization:[],
				orgNo:"320481",
				name:"溧阳市"
			}, {
				organization:[],
				orgNo:"320482",
				name:"金坛市"
			} ],
			orgNo:"3204",
			name:"常州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"320502",
				name:"沧浪区"
			}, {
				organization:[],
				orgNo:"320503",
				name:"平江区"
			}, {
				organization:[],
				orgNo:"320504",
				name:"金阊区"
			}, {
				organization:[],
				orgNo:"320505",
				name:"虎丘区"
			}, {
				organization:[],
				orgNo:"320506",
				name:"吴中区"
			}, {
				organization:[],
				orgNo:"320507",
				name:"相城区"
			}, {
				organization:[],
				orgNo:"320581",
				name:"常熟市"
			}, {
				organization:[],
				orgNo:"320582",
				name:"张家港市"
			}, {
				organization:[],
				orgNo:"320583",
				name:"昆山市"
			}, {
				organization:[],
				orgNo:"320584",
				name:"吴江市"
			}, {
				organization:[],
				orgNo:"320585",
				name:"太仓市"
			} ],
			orgNo:"3205",
			name:"苏州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"320602",
				name:"崇川区"
			}, {
				organization:[],
				orgNo:"320611",
				name:"港闸区"
			}, {
				organization:[],
				orgNo:"320621",
				name:"海安县"
			}, {
				organization:[],
				orgNo:"320623",
				name:"如东县"
			}, {
				organization:[],
				orgNo:"320681",
				name:"启东市"
			}, {
				organization:[],
				orgNo:"320682",
				name:"如皋市"
			}, {
				organization:[],
				orgNo:"320683",
				name:"通州市"
			}, {
				organization:[],
				orgNo:"320684",
				name:"海门市"
			} ],
			orgNo:"3206",
			name:"南通市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"320703",
				name:"连云区"
			}, {
				organization:[],
				orgNo:"320705",
				name:"新浦区"
			}, {
				organization:[],
				orgNo:"320706",
				name:"海州区"
			}, {
				organization:[],
				orgNo:"320721",
				name:"赣榆县"
			}, {
				organization:[],
				orgNo:"320722",
				name:"东海县"
			}, {
				organization:[],
				orgNo:"320723",
				name:"灌云县"
			}, {
				organization:[],
				orgNo:"320724",
				name:"灌南县"
			} ],
			orgNo:"3207",
			name:"连云港市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"320802",
				name:"清河区"
			}, {
				organization:[],
				orgNo:"320803",
				name:"楚州区"
			}, {
				organization:[],
				orgNo:"320804",
				name:"淮阴区"
			}, {
				organization:[],
				orgNo:"320811",
				name:"清浦区"
			}, {
				organization:[],
				orgNo:"320826",
				name:"涟水县"
			}, {
				organization:[],
				orgNo:"320829",
				name:"洪泽县"
			}, {
				organization:[],
				orgNo:"320830",
				name:"盱眙县"
			}, {
				organization:[],
				orgNo:"320831",
				name:"金湖县"
			} ],
			orgNo:"3208",
			name:"淮安市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"320902",
				name:"亭湖区"
			}, {
				organization:[],
				orgNo:"320903",
				name:"盐都区"
			}, {
				organization:[],
				orgNo:"320921",
				name:"响水县"
			}, {
				organization:[],
				orgNo:"320922",
				name:"滨海县"
			}, {
				organization:[],
				orgNo:"320923",
				name:"阜宁县"
			}, {
				organization:[],
				orgNo:"320924",
				name:"射阳县"
			}, {
				organization:[],
				orgNo:"320925",
				name:"建湖县"
			}, {
				organization:[],
				orgNo:"320981",
				name:"东台市"
			}, {
				organization:[],
				orgNo:"320982",
				name:"大丰市"
			} ],
			orgNo:"3209",
			name:"盐城市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"321002",
				name:"广陵区"
			}, {
				organization:[],
				orgNo:"321003",
				name:"邗江区"
			}, {
				organization:[],
				orgNo:"321011",
				name:"维扬区"
			}, {
				organization:[],
				orgNo:"321023",
				name:"宝应县"
			}, {
				organization:[],
				orgNo:"321081",
				name:"仪征市"
			}, {
				organization:[],
				orgNo:"321084",
				name:"高邮市"
			}, {
				organization:[],
				orgNo:"321088",
				name:"江都市"
			} ],
			orgNo:"3210",
			name:"扬州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"321102",
				name:"京口区"
			}, {
				organization:[],
				orgNo:"321111",
				name:"润州区"
			}, {
				organization:[],
				orgNo:"321112",
				name:"丹徒区"
			}, {
				organization:[],
				orgNo:"321181",
				name:"丹阳市"
			}, {
				organization:[],
				orgNo:"321182",
				name:"扬中市"
			}, {
				organization:[],
				orgNo:"321183",
				name:"句容市"
			} ],
			orgNo:"3211",
			name:"镇江市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"321202",
				name:"海陵区"
			}, {
				organization:[],
				orgNo:"321203",
				name:"高港区"
			}, {
				organization:[],
				orgNo:"321281",
				name:"兴化市"
			}, {
				organization:[],
				orgNo:"321282",
				name:"靖江市"
			}, {
				organization:[],
				orgNo:"321283",
				name:"泰兴市"
			}, {
				organization:[],
				orgNo:"321284",
				name:"姜堰市"
			} ],
			orgNo:"3212",
			name:"泰州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"321302",
				name:"宿城区"
			}, {
				organization:[],
				orgNo:"321311",
				name:"宿豫区"
			}, {
				organization:[],
				orgNo:"321322",
				name:"沭阳县"
			}, {
				organization:[],
				orgNo:"321323",
				name:"泗阳县"
			}, {
				organization:[],
				orgNo:"321324",
				name:"泗洪县"
			} ],
			orgNo:"3213",
			name:"宿迁市"
		} ],
		orgNo:"32",
		name:"江苏"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"330102",
				name:"上城区"
			}, {
				organization:[],
				orgNo:"330103",
				name:"下城区"
			}, {
				organization:[],
				orgNo:"330104",
				name:"江干区"
			}, {
				organization:[],
				orgNo:"330105",
				name:"拱墅区"
			}, {
				organization:[],
				orgNo:"330106",
				name:"西湖区"
			}, {
				organization:[],
				orgNo:"330108",
				name:"滨江区"
			}, {
				organization:[],
				orgNo:"330109",
				name:"萧山区"
			}, {
				organization:[],
				orgNo:"330110",
				name:"余杭区"
			}, {
				organization:[],
				orgNo:"330122",
				name:"桐庐县"
			}, {
				organization:[],
				orgNo:"330127",
				name:"淳安县"
			}, {
				organization:[],
				orgNo:"330182",
				name:"建德市"
			}, {
				organization:[],
				orgNo:"330183",
				name:"富阳市"
			}, {
				organization:[],
				orgNo:"330185",
				name:"临安市"
			} ],
			orgNo:"3301",
			name:"杭州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"330203",
				name:"海曙区"
			}, {
				organization:[],
				orgNo:"330204",
				name:"江东区"
			}, {
				organization:[],
				orgNo:"330205",
				name:"江北区"
			}, {
				organization:[],
				orgNo:"330206",
				name:"北仑区"
			}, {
				organization:[],
				orgNo:"330211",
				name:"镇海区"
			}, {
				organization:[],
				orgNo:"330212",
				name:"鄞州区"
			}, {
				organization:[],
				orgNo:"330225",
				name:"象山县"
			}, {
				organization:[],
				orgNo:"330226",
				name:"宁海县"
			}, {
				organization:[],
				orgNo:"330281",
				name:"余姚市"
			}, {
				organization:[],
				orgNo:"330282",
				name:"慈溪市"
			}, {
				organization:[],
				orgNo:"330283",
				name:"奉化市"
			} ],
			orgNo:"3302",
			name:"宁波市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"330302",
				name:"鹿城区"
			}, {
				organization:[],
				orgNo:"330303",
				name:"龙湾区"
			}, {
				organization:[],
				orgNo:"330304",
				name:"瓯海区"
			}, {
				organization:[],
				orgNo:"330322",
				name:"洞头县"
			}, {
				organization:[],
				orgNo:"330324",
				name:"永嘉县"
			}, {
				organization:[],
				orgNo:"330326",
				name:"平阳县"
			}, {
				organization:[],
				orgNo:"330327",
				name:"苍南县"
			}, {
				organization:[],
				orgNo:"330328",
				name:"文成县"
			}, {
				organization:[],
				orgNo:"330329",
				name:"泰顺县"
			}, {
				organization:[],
				orgNo:"330381",
				name:"瑞安市"
			}, {
				organization:[],
				orgNo:"330382",
				name:"乐清市"
			} ],
			orgNo:"3303",
			name:"温州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"330411",
				name:"秀洲区"
			}, {
				organization:[],
				orgNo:"330421",
				name:"嘉善县"
			}, {
				organization:[],
				orgNo:"330424",
				name:"海盐县"
			}, {
				organization:[],
				orgNo:"330481",
				name:"海宁市"
			}, {
				organization:[],
				orgNo:"330482",
				name:"平湖市"
			}, {
				organization:[],
				orgNo:"330483",
				name:"桐乡市"
			}, {
				organization:[],
				orgNo:"330484",
				name:"南湖区"
			} ],
			orgNo:"3304",
			name:"嘉兴市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"330502",
				name:"吴兴区"
			}, {
				organization:[],
				orgNo:"330503",
				name:"南浔区"
			}, {
				organization:[],
				orgNo:"330521",
				name:"德清县"
			}, {
				organization:[],
				orgNo:"330522",
				name:"长兴县"
			}, {
				organization:[],
				orgNo:"330523",
				name:"安吉县"
			} ],
			orgNo:"3305",
			name:"湖州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"330602",
				name:"越城区"
			}, {
				organization:[],
				orgNo:"330621",
				name:"绍兴县"
			}, {
				organization:[],
				orgNo:"330624",
				name:"新昌县"
			}, {
				organization:[],
				orgNo:"330681",
				name:"诸暨市"
			}, {
				organization:[],
				orgNo:"330682",
				name:"上虞市"
			}, {
				organization:[],
				orgNo:"330683",
				name:"嵊州市"
			} ],
			orgNo:"3306",
			name:"绍兴市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"330702",
				name:"婺城区"
			}, {
				organization:[],
				orgNo:"330703",
				name:"金东区"
			}, {
				organization:[],
				orgNo:"330723",
				name:"武义县"
			}, {
				organization:[],
				orgNo:"330726",
				name:"浦江县"
			}, {
				organization:[],
				orgNo:"330727",
				name:"磐安县"
			}, {
				organization:[],
				orgNo:"330781",
				name:"兰溪市"
			}, {
				organization:[],
				orgNo:"330782",
				name:"义乌市"
			}, {
				organization:[],
				orgNo:"330783",
				name:"东阳市"
			}, {
				organization:[],
				orgNo:"330784",
				name:"永康市"
			} ],
			orgNo:"3307",
			name:"金华市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"330802",
				name:"柯城区"
			}, {
				organization:[],
				orgNo:"330803",
				name:"衢江区"
			}, {
				organization:[],
				orgNo:"330822",
				name:"常山县"
			}, {
				organization:[],
				orgNo:"330824",
				name:"开化县"
			}, {
				organization:[],
				orgNo:"330825",
				name:"龙游县"
			}, {
				organization:[],
				orgNo:"330881",
				name:"江山市"
			} ],
			orgNo:"3308",
			name:"衢州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"330902",
				name:"定海区"
			}, {
				organization:[],
				orgNo:"330903",
				name:"普陀区"
			}, {
				organization:[],
				orgNo:"330921",
				name:"岱山县"
			}, {
				organization:[],
				orgNo:"330922",
				name:"嵊泗县"
			} ],
			orgNo:"3309",
			name:"舟山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"331002",
				name:"椒江区"
			}, {
				organization:[],
				orgNo:"331003",
				name:"黄岩区"
			}, {
				organization:[],
				orgNo:"331004",
				name:"路桥区"
			}, {
				organization:[],
				orgNo:"331021",
				name:"玉环县"
			}, {
				organization:[],
				orgNo:"331022",
				name:"三门县"
			}, {
				organization:[],
				orgNo:"331023",
				name:"天台县"
			}, {
				organization:[],
				orgNo:"331024",
				name:"仙居县"
			}, {
				organization:[],
				orgNo:"331081",
				name:"温岭市"
			}, {
				organization:[],
				orgNo:"331082",
				name:"临海市"
			} ],
			orgNo:"3310",
			name:"台州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"331102",
				name:"莲都区"
			}, {
				organization:[],
				orgNo:"331121",
				name:"青田县"
			}, {
				organization:[],
				orgNo:"331122",
				name:"缙云县"
			}, {
				organization:[],
				orgNo:"331123",
				name:"遂昌县"
			}, {
				organization:[],
				orgNo:"331124",
				name:"松阳县"
			}, {
				organization:[],
				orgNo:"331125",
				name:"云和县"
			}, {
				organization:[],
				orgNo:"331126",
				name:"庆元县"
			}, {
				organization:[],
				orgNo:"331127",
				name:"景宁畲族自治县"
			}, {
				organization:[],
				orgNo:"331181",
				name:"龙泉市"
			} ],
			orgNo:"3311",
			name:"丽水市"
		} ],
		orgNo:"33",
		name:"浙江"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"340102",
				name:"瑶海区"
			}, {
				organization:[],
				orgNo:"340103",
				name:"庐阳区"
			}, {
				organization:[],
				orgNo:"340104",
				name:"蜀山区"
			}, {
				organization:[],
				orgNo:"340111",
				name:"包河区"
			}, {
				organization:[],
				orgNo:"340121",
				name:"长丰县"
			}, {
				organization:[],
				orgNo:"340122",
				name:"肥东县"
			}, {
				organization:[],
				orgNo:"340123",
				name:"肥西县"
			} ],
			orgNo:"3401",
			name:"合肥市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"340202",
				name:"镜湖区"
			}, {
				organization:[],
				orgNo:"340203",
				name:"弋江区"
			}, {
				organization:[],
				orgNo:"340207",
				name:"鸠江区"
			}, {
				organization:[],
				orgNo:"340208",
				name:"三山区"
			}, {
				organization:[],
				orgNo:"340221",
				name:"芜湖县"
			}, {
				organization:[],
				orgNo:"340222",
				name:"繁昌县"
			}, {
				organization:[],
				orgNo:"340223",
				name:"南陵县"
			} ],
			orgNo:"3402",
			name:"芜湖市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"340302",
				name:"龙子湖区"
			}, {
				organization:[],
				orgNo:"340303",
				name:"蚌山区"
			}, {
				organization:[],
				orgNo:"340304",
				name:"禹会区"
			}, {
				organization:[],
				orgNo:"340311",
				name:"淮上区"
			}, {
				organization:[],
				orgNo:"340321",
				name:"怀远县"
			}, {
				organization:[],
				orgNo:"340322",
				name:"五河县"
			}, {
				organization:[],
				orgNo:"340323",
				name:"固镇县"
			} ],
			orgNo:"3403",
			name:"蚌埠市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"340402",
				name:"大通区"
			}, {
				organization:[],
				orgNo:"340403",
				name:"田家庵区"
			}, {
				organization:[],
				orgNo:"340404",
				name:"谢家集区"
			}, {
				organization:[],
				orgNo:"340405",
				name:"八公山区"
			}, {
				organization:[],
				orgNo:"340406",
				name:"潘集区"
			}, {
				organization:[],
				orgNo:"340421",
				name:"凤台县"
			} ],
			orgNo:"3404",
			name:"淮南市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"340502",
				name:"金家庄区"
			}, {
				organization:[],
				orgNo:"340503",
				name:"花山区"
			}, {
				organization:[],
				orgNo:"340504",
				name:"雨山区"
			}, {
				organization:[],
				orgNo:"340521",
				name:"当涂县"
			} ],
			orgNo:"3405",
			name:"马鞍山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"340602",
				name:"杜集区"
			}, {
				organization:[],
				orgNo:"340603",
				name:"相山区"
			}, {
				organization:[],
				orgNo:"340604",
				name:"烈山区"
			}, {
				organization:[],
				orgNo:"340621",
				name:"濉溪县"
			} ],
			orgNo:"3406",
			name:"淮北市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"340702",
				name:"铜官山区"
			}, {
				organization:[],
				orgNo:"340703",
				name:"狮子山区"
			}, {
				organization:[],
				orgNo:"340711",
				name:"郊区"
			}, {
				organization:[],
				orgNo:"340721",
				name:"铜陵县"
			} ],
			orgNo:"3407",
			name:"铜陵市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"340802",
				name:"迎江区"
			}, {
				organization:[],
				orgNo:"340803",
				name:"大观区"
			}, {
				organization:[],
				orgNo:"340811",
				name:"宜秀区"
			}, {
				organization:[],
				orgNo:"340822",
				name:"怀宁县"
			}, {
				organization:[],
				orgNo:"340823",
				name:"枞阳县"
			}, {
				organization:[],
				orgNo:"340824",
				name:"潜山县"
			}, {
				organization:[],
				orgNo:"340825",
				name:"太湖县"
			}, {
				organization:[],
				orgNo:"340826",
				name:"宿松县"
			}, {
				organization:[],
				orgNo:"340827",
				name:"望江县"
			}, {
				organization:[],
				orgNo:"340828",
				name:"岳西县"
			}, {
				organization:[],
				orgNo:"340881",
				name:"桐城市"
			} ],
			orgNo:"3408",
			name:"安庆市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"341002",
				name:"屯溪区"
			}, {
				organization:[],
				orgNo:"341003",
				name:"黄山区"
			}, {
				organization:[],
				orgNo:"341004",
				name:"徽州区"
			}, {
				organization:[],
				orgNo:"341021",
				name:"歙县"
			}, {
				organization:[],
				orgNo:"341022",
				name:"休宁县"
			}, {
				organization:[],
				orgNo:"341023",
				name:"黟县"
			}, {
				organization:[],
				orgNo:"341024",
				name:"祁门县"
			} ],
			orgNo:"3410",
			name:"黄山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"341102",
				name:"琅琊区"
			}, {
				organization:[],
				orgNo:"341103",
				name:"南谯区"
			}, {
				organization:[],
				orgNo:"341122",
				name:"来安县"
			}, {
				organization:[],
				orgNo:"341124",
				name:"全椒县"
			}, {
				organization:[],
				orgNo:"341125",
				name:"定远县"
			}, {
				organization:[],
				orgNo:"341126",
				name:"凤阳县"
			}, {
				organization:[],
				orgNo:"341181",
				name:"天长市"
			}, {
				organization:[],
				orgNo:"341182",
				name:"明光市"
			} ],
			orgNo:"3411",
			name:"滁州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"341202",
				name:"颍州区"
			}, {
				organization:[],
				orgNo:"341203",
				name:"颍东区"
			}, {
				organization:[],
				orgNo:"341204",
				name:"颍泉区"
			}, {
				organization:[],
				orgNo:"341221",
				name:"临泉县"
			}, {
				organization:[],
				orgNo:"341222",
				name:"太和县"
			}, {
				organization:[],
				orgNo:"341225",
				name:"阜南县"
			}, {
				organization:[],
				orgNo:"341226",
				name:"颍上县"
			}, {
				organization:[],
				orgNo:"341282",
				name:"界首市"
			} ],
			orgNo:"3412",
			name:"阜阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"341302",
				name:"埇桥区"
			}, {
				organization:[],
				orgNo:"341321",
				name:"砀山县"
			}, {
				organization:[],
				orgNo:"341322",
				name:"萧县"
			}, {
				organization:[],
				orgNo:"341323",
				name:"灵璧县"
			}, {
				organization:[],
				orgNo:"341324",
				name:"泗县"
			} ],
			orgNo:"3413",
			name:"宿州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"341402",
				name:"居巢区"
			}, {
				organization:[],
				orgNo:"341421",
				name:"庐江县"
			}, {
				organization:[],
				orgNo:"341422",
				name:"无为县"
			}, {
				organization:[],
				orgNo:"341423",
				name:"含山县"
			}, {
				organization:[],
				orgNo:"341424",
				name:"和县"
			} ],
			orgNo:"3414",
			name:"巢湖市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"341502",
				name:"金安区"
			}, {
				organization:[],
				orgNo:"341503",
				name:"裕安区"
			}, {
				organization:[],
				orgNo:"341521",
				name:"寿县"
			}, {
				organization:[],
				orgNo:"341522",
				name:"霍邱县"
			}, {
				organization:[],
				orgNo:"341523",
				name:"舒城县"
			}, {
				organization:[],
				orgNo:"341524",
				name:"金寨县"
			}, {
				organization:[],
				orgNo:"341525",
				name:"霍山县"
			} ],
			orgNo:"3415",
			name:"六安市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"341602",
				name:"谯城区"
			}, {
				organization:[],
				orgNo:"341621",
				name:"涡阳县"
			}, {
				organization:[],
				orgNo:"341622",
				name:"蒙城县"
			}, {
				organization:[],
				orgNo:"341623",
				name:"利辛县"
			} ],
			orgNo:"3416",
			name:"亳州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"341702",
				name:"贵池区"
			}, {
				organization:[],
				orgNo:"341721",
				name:"东至县"
			}, {
				organization:[],
				orgNo:"341722",
				name:"石台县"
			}, {
				organization:[],
				orgNo:"341723",
				name:"青阳县"
			} ],
			orgNo:"3417",
			name:"池州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"341802",
				name:"宣州区"
			}, {
				organization:[],
				orgNo:"341821",
				name:"郎溪县"
			}, {
				organization:[],
				orgNo:"341822",
				name:"广德县"
			}, {
				organization:[],
				orgNo:"341823",
				name:"泾县"
			}, {
				organization:[],
				orgNo:"341824",
				name:"绩溪县"
			}, {
				organization:[],
				orgNo:"341825",
				name:"旌德县"
			}, {
				organization:[],
				orgNo:"341881",
				name:"宁国市"
			} ],
			orgNo:"3418",
			name:"宣城市"
		} ],
		orgNo:"34",
		name:"安徽"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"350102",
				name:"鼓楼区"
			}, {
				organization:[],
				orgNo:"350103",
				name:"台江区"
			}, {
				organization:[],
				orgNo:"350104",
				name:"仓山区"
			}, {
				organization:[],
				orgNo:"350105",
				name:"马尾区"
			}, {
				organization:[],
				orgNo:"350111",
				name:"晋安区"
			}, {
				organization:[],
				orgNo:"350121",
				name:"闽侯县"
			}, {
				organization:[],
				orgNo:"350122",
				name:"连江县"
			}, {
				organization:[],
				orgNo:"350123",
				name:"罗源县"
			}, {
				organization:[],
				orgNo:"350124",
				name:"闽清县"
			}, {
				organization:[],
				orgNo:"350125",
				name:"永泰县"
			}, {
				organization:[],
				orgNo:"350128",
				name:"平潭县"
			}, {
				organization:[],
				orgNo:"350181",
				name:"福清市"
			}, {
				organization:[],
				orgNo:"350182",
				name:"长乐市"
			} ],
			orgNo:"3501",
			name:"福州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"350203",
				name:"思明区"
			}, {
				organization:[],
				orgNo:"350205",
				name:"海沧区"
			}, {
				organization:[],
				orgNo:"350206",
				name:"湖里区"
			}, {
				organization:[],
				orgNo:"350211",
				name:"集美区"
			}, {
				organization:[],
				orgNo:"350212",
				name:"同安区"
			}, {
				organization:[],
				orgNo:"350213",
				name:"翔安区"
			} ],
			orgNo:"3502",
			name:"厦门市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"350302",
				name:"城厢区"
			}, {
				organization:[],
				orgNo:"350303",
				name:"涵江区"
			}, {
				organization:[],
				orgNo:"350304",
				name:"荔城区"
			}, {
				organization:[],
				orgNo:"350305",
				name:"秀屿区"
			}, {
				organization:[],
				orgNo:"350322",
				name:"仙游县"
			} ],
			orgNo:"3503",
			name:"莆田市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"350402",
				name:"梅列区"
			}, {
				organization:[],
				orgNo:"350403",
				name:"三元区"
			}, {
				organization:[],
				orgNo:"350421",
				name:"明溪县"
			}, {
				organization:[],
				orgNo:"350423",
				name:"清流县"
			}, {
				organization:[],
				orgNo:"350424",
				name:"宁化县"
			}, {
				organization:[],
				orgNo:"350425",
				name:"大田县"
			}, {
				organization:[],
				orgNo:"350426",
				name:"尤溪县"
			}, {
				organization:[],
				orgNo:"350427",
				name:"沙县"
			}, {
				organization:[],
				orgNo:"350428",
				name:"将乐县"
			}, {
				organization:[],
				orgNo:"350429",
				name:"泰宁县"
			}, {
				organization:[],
				orgNo:"350430",
				name:"建宁县"
			}, {
				organization:[],
				orgNo:"350481",
				name:"永安市"
			} ],
			orgNo:"3504",
			name:"三明市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"350502",
				name:"鲤城区"
			}, {
				organization:[],
				orgNo:"350503",
				name:"丰泽区"
			}, {
				organization:[],
				orgNo:"350504",
				name:"洛江区"
			}, {
				organization:[],
				orgNo:"350505",
				name:"泉港区"
			}, {
				organization:[],
				orgNo:"350521",
				name:"惠安县"
			}, {
				organization:[],
				orgNo:"350524",
				name:"安溪县"
			}, {
				organization:[],
				orgNo:"350525",
				name:"永春县"
			}, {
				organization:[],
				orgNo:"350526",
				name:"德化县"
			}, {
				organization:[],
				orgNo:"350527",
				name:"金门县"
			}, {
				organization:[],
				orgNo:"350581",
				name:"石狮市"
			}, {
				organization:[],
				orgNo:"350582",
				name:"晋江市"
			}, {
				organization:[],
				orgNo:"350583",
				name:"南安市"
			} ],
			orgNo:"3505",
			name:"泉州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"350602",
				name:"芗城区"
			}, {
				organization:[],
				orgNo:"350603",
				name:"龙文区"
			}, {
				organization:[],
				orgNo:"350622",
				name:"云霄县"
			}, {
				organization:[],
				orgNo:"350623",
				name:"漳浦县"
			}, {
				organization:[],
				orgNo:"350624",
				name:"诏安县"
			}, {
				organization:[],
				orgNo:"350625",
				name:"长泰县"
			}, {
				organization:[],
				orgNo:"350626",
				name:"东山县"
			}, {
				organization:[],
				orgNo:"350627",
				name:"南靖县"
			}, {
				organization:[],
				orgNo:"350628",
				name:"平和县"
			}, {
				organization:[],
				orgNo:"350629",
				name:"华安县"
			}, {
				organization:[],
				orgNo:"350681",
				name:"龙海市"
			} ],
			orgNo:"3506",
			name:"漳州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"350702",
				name:"延平区"
			}, {
				organization:[],
				orgNo:"350721",
				name:"顺昌县"
			}, {
				organization:[],
				orgNo:"350722",
				name:"浦城县"
			}, {
				organization:[],
				orgNo:"350723",
				name:"光泽县"
			}, {
				organization:[],
				orgNo:"350724",
				name:"松溪县"
			}, {
				organization:[],
				orgNo:"350725",
				name:"政和县"
			}, {
				organization:[],
				orgNo:"350781",
				name:"邵武市"
			}, {
				organization:[],
				orgNo:"350782",
				name:"武夷山市"
			}, {
				organization:[],
				orgNo:"350783",
				name:"建瓯市"
			}, {
				organization:[],
				orgNo:"350784",
				name:"建阳市"
			} ],
			orgNo:"3507",
			name:"南平市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"350802",
				name:"新罗区"
			}, {
				organization:[],
				orgNo:"350821",
				name:"长汀县"
			}, {
				organization:[],
				orgNo:"350822",
				name:"永定县"
			}, {
				organization:[],
				orgNo:"350823",
				name:"上杭县"
			}, {
				organization:[],
				orgNo:"350824",
				name:"武平县"
			}, {
				organization:[],
				orgNo:"350825",
				name:"连城县"
			}, {
				organization:[],
				orgNo:"350881",
				name:"漳平市"
			} ],
			orgNo:"3508",
			name:"龙岩市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"350902",
				name:"蕉城区"
			}, {
				organization:[],
				orgNo:"350921",
				name:"霞浦县"
			}, {
				organization:[],
				orgNo:"350922",
				name:"古田县"
			}, {
				organization:[],
				orgNo:"350923",
				name:"屏南县"
			}, {
				organization:[],
				orgNo:"350924",
				name:"寿宁县"
			}, {
				organization:[],
				orgNo:"350925",
				name:"周宁县"
			}, {
				organization:[],
				orgNo:"350926",
				name:"柘荣县"
			}, {
				organization:[],
				orgNo:"350981",
				name:"福安市"
			}, {
				organization:[],
				orgNo:"350982",
				name:"福鼎市"
			} ],
			orgNo:"3509",
			name:"宁德市"
		} ],
		orgNo:"35",
		name:"福建"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"360102",
				name:"东湖区"
			}, {
				organization:[],
				orgNo:"360103",
				name:"西湖区"
			}, {
				organization:[],
				orgNo:"360104",
				name:"青云谱区"
			}, {
				organization:[],
				orgNo:"360105",
				name:"湾里区"
			}, {
				organization:[],
				orgNo:"360111",
				name:"青山湖区"
			}, {
				organization:[],
				orgNo:"360121",
				name:"南昌县"
			}, {
				organization:[],
				orgNo:"360122",
				name:"新建县"
			}, {
				organization:[],
				orgNo:"360123",
				name:"安义县"
			}, {
				organization:[],
				orgNo:"360124",
				name:"进贤县"
			}, {
				organization:[],
				orgNo:"360125",
				name:"经济技术开发区"
			}, {
				organization:[],
				orgNo:"360126",
				name:"红谷滩新区"
			} ],
			orgNo:"3601",
			name:"南昌市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"360202",
				name:"昌江区"
			}, {
				organization:[],
				orgNo:"360203",
				name:"珠山区"
			}, {
				organization:[],
				orgNo:"360222",
				name:"浮梁县"
			}, {
				organization:[],
				orgNo:"360281",
				name:"乐平市"
			} ],
			orgNo:"3602",
			name:"景德镇市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"360302",
				name:"安源区"
			}, {
				organization:[],
				orgNo:"360313",
				name:"湘东区"
			}, {
				organization:[],
				orgNo:"360321",
				name:"莲花县"
			}, {
				organization:[],
				orgNo:"360322",
				name:"上栗县"
			}, {
				organization:[],
				orgNo:"360323",
				name:"芦溪县"
			} ],
			orgNo:"3603",
			name:"萍乡市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"360402",
				name:"庐山区"
			}, {
				organization:[],
				orgNo:"360403",
				name:"浔阳区"
			}, {
				organization:[],
				orgNo:"360421",
				name:"九江县"
			}, {
				organization:[],
				orgNo:"360423",
				name:"武宁县"
			}, {
				organization:[],
				orgNo:"360424",
				name:"修水县"
			}, {
				organization:[],
				orgNo:"360425",
				name:"永修县"
			}, {
				organization:[],
				orgNo:"360426",
				name:"德安县"
			}, {
				organization:[],
				orgNo:"360427",
				name:"星子县"
			}, {
				organization:[],
				orgNo:"360428",
				name:"都昌县"
			}, {
				organization:[],
				orgNo:"360429",
				name:"湖口县"
			}, {
				organization:[],
				orgNo:"360430",
				name:"彭泽县"
			}, {
				organization:[],
				orgNo:"360481",
				name:"瑞昌市"
			} ],
			orgNo:"3604",
			name:"九江市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"360502",
				name:"渝水区"
			}, {
				organization:[],
				orgNo:"360521",
				name:"分宜县"
			} ],
			orgNo:"3605",
			name:"新余市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"360602",
				name:"月湖区"
			}, {
				organization:[],
				orgNo:"360622",
				name:"余江县"
			}, {
				organization:[],
				orgNo:"360681",
				name:"贵溪市"
			} ],
			orgNo:"3606",
			name:"鹰潭市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"360702",
				name:"章贡区"
			}, {
				organization:[],
				orgNo:"360721",
				name:"赣县"
			}, {
				organization:[],
				orgNo:"360722",
				name:"信丰县"
			}, {
				organization:[],
				orgNo:"360723",
				name:"大余县"
			}, {
				organization:[],
				orgNo:"360724",
				name:"上犹县"
			}, {
				organization:[],
				orgNo:"360725",
				name:"崇义县"
			}, {
				organization:[],
				orgNo:"360726",
				name:"安远县"
			}, {
				organization:[],
				orgNo:"360727",
				name:"龙南县"
			}, {
				organization:[],
				orgNo:"360728",
				name:"定南县"
			}, {
				organization:[],
				orgNo:"360729",
				name:"全南县"
			}, {
				organization:[],
				orgNo:"360730",
				name:"宁都县"
			}, {
				organization:[],
				orgNo:"360731",
				name:"于都县"
			}, {
				organization:[],
				orgNo:"360732",
				name:"兴国县"
			}, {
				organization:[],
				orgNo:"360733",
				name:"会昌县"
			}, {
				organization:[],
				orgNo:"360734",
				name:"寻乌县"
			}, {
				organization:[],
				orgNo:"360735",
				name:"石城县"
			}, {
				organization:[],
				orgNo:"360781",
				name:"瑞金市"
			}, {
				organization:[],
				orgNo:"360782",
				name:"南康市"
			} ],
			orgNo:"3607",
			name:"赣州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"360802",
				name:"吉州区"
			}, {
				organization:[],
				orgNo:"360803",
				name:"青原区"
			}, {
				organization:[],
				orgNo:"360821",
				name:"吉安县"
			}, {
				organization:[],
				orgNo:"360822",
				name:"吉水县"
			}, {
				organization:[],
				orgNo:"360823",
				name:"峡江县"
			}, {
				organization:[],
				orgNo:"360824",
				name:"新干县"
			}, {
				organization:[],
				orgNo:"360825",
				name:"永丰县"
			}, {
				organization:[],
				orgNo:"360826",
				name:"泰和县"
			}, {
				organization:[],
				orgNo:"360827",
				name:"遂川县"
			}, {
				organization:[],
				orgNo:"360828",
				name:"万安县"
			}, {
				organization:[],
				orgNo:"360829",
				name:"安福县"
			}, {
				organization:[],
				orgNo:"360830",
				name:"永新县"
			}, {
				organization:[],
				orgNo:"360881",
				name:"井冈山市"
			} ],
			orgNo:"3608",
			name:"吉安市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"360902",
				name:"袁州区"
			}, {
				organization:[],
				orgNo:"360921",
				name:"奉新县"
			}, {
				organization:[],
				orgNo:"360922",
				name:"万载县"
			}, {
				organization:[],
				orgNo:"360923",
				name:"上高县"
			}, {
				organization:[],
				orgNo:"360924",
				name:"宜丰县"
			}, {
				organization:[],
				orgNo:"360925",
				name:"靖安县"
			}, {
				organization:[],
				orgNo:"360926",
				name:"铜鼓县"
			}, {
				organization:[],
				orgNo:"360981",
				name:"丰城市"
			}, {
				organization:[],
				orgNo:"360982",
				name:"樟树市"
			}, {
				organization:[],
				orgNo:"360983",
				name:"高安市"
			} ],
			orgNo:"3609",
			name:"宜春市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"361002",
				name:"临川区"
			}, {
				organization:[],
				orgNo:"361021",
				name:"南城县"
			}, {
				organization:[],
				orgNo:"361022",
				name:"黎川县"
			}, {
				organization:[],
				orgNo:"361023",
				name:"南丰县"
			}, {
				organization:[],
				orgNo:"361024",
				name:"崇仁县"
			}, {
				organization:[],
				orgNo:"361025",
				name:"乐安县"
			}, {
				organization:[],
				orgNo:"361026",
				name:"宜黄县"
			}, {
				organization:[],
				orgNo:"361027",
				name:"金溪县"
			}, {
				organization:[],
				orgNo:"361028",
				name:"资溪县"
			}, {
				organization:[],
				orgNo:"361029",
				name:"东乡县"
			}, {
				organization:[],
				orgNo:"361030",
				name:"广昌县"
			} ],
			orgNo:"3610",
			name:"抚州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"361102",
				name:"信州区"
			}, {
				organization:[],
				orgNo:"361121",
				name:"上饶县"
			}, {
				organization:[],
				orgNo:"361122",
				name:"广丰县"
			}, {
				organization:[],
				orgNo:"361123",
				name:"玉山县"
			}, {
				organization:[],
				orgNo:"361124",
				name:"铅山县"
			}, {
				organization:[],
				orgNo:"361125",
				name:"横峰县"
			}, {
				organization:[],
				orgNo:"361126",
				name:"弋阳县"
			}, {
				organization:[],
				orgNo:"361127",
				name:"余干县"
			}, {
				organization:[],
				orgNo:"361128",
				name:"鄱阳县"
			}, {
				organization:[],
				orgNo:"361129",
				name:"万年县"
			}, {
				organization:[],
				orgNo:"361130",
				name:"婺源县"
			}, {
				organization:[],
				orgNo:"361181",
				name:"德兴市"
			} ],
			orgNo:"3611",
			name:"上饶市"
		} ],
		orgNo:"36",
		name:"江西"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"370102",
				name:"历下区"
			}, {
				organization:[],
				orgNo:"370103",
				name:"市中区"
			}, {
				organization:[],
				orgNo:"370104",
				name:"槐荫区"
			}, {
				organization:[],
				orgNo:"370105",
				name:"天桥区"
			}, {
				organization:[],
				orgNo:"370112",
				name:"历城区"
			}, {
				organization:[],
				orgNo:"370113",
				name:"长清区"
			}, {
				organization:[],
				orgNo:"370124",
				name:"平阴县"
			}, {
				organization:[],
				orgNo:"370125",
				name:"济阳县"
			}, {
				organization:[],
				orgNo:"370126",
				name:"商河县"
			}, {
				organization:[],
				orgNo:"370181",
				name:"章丘市"
			} ],
			orgNo:"3701",
			name:"济南市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"370202",
				name:"市南区"
			}, {
				organization:[],
				orgNo:"370203",
				name:"市北区"
			}, {
				organization:[],
				orgNo:"370205",
				name:"四方区"
			}, {
				organization:[],
				orgNo:"370211",
				name:"黄岛区"
			}, {
				organization:[],
				orgNo:"370212",
				name:"崂山区"
			}, {
				organization:[],
				orgNo:"370213",
				name:"李沧区"
			}, {
				organization:[],
				orgNo:"370214",
				name:"城阳区"
			}, {
				organization:[],
				orgNo:"370281",
				name:"胶州市"
			}, {
				organization:[],
				orgNo:"370282",
				name:"即墨市"
			}, {
				organization:[],
				orgNo:"370283",
				name:"平度市"
			}, {
				organization:[],
				orgNo:"370284",
				name:"胶南市"
			}, {
				organization:[],
				orgNo:"370285",
				name:"莱西市"
			} ],
			orgNo:"3702",
			name:"青岛市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"370302",
				name:"淄川区"
			}, {
				organization:[],
				orgNo:"370303",
				name:"张店区"
			}, {
				organization:[],
				orgNo:"370304",
				name:"博山区"
			}, {
				organization:[],
				orgNo:"370305",
				name:"临淄区"
			}, {
				organization:[],
				orgNo:"370306",
				name:"周村区"
			}, {
				organization:[],
				orgNo:"370321",
				name:"桓台县"
			}, {
				organization:[],
				orgNo:"370322",
				name:"高青县"
			}, {
				organization:[],
				orgNo:"370323",
				name:"沂源县"
			} ],
			orgNo:"3703",
			name:"淄博市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"370402",
				name:"市中区"
			}, {
				organization:[],
				orgNo:"370403",
				name:"薛城区"
			}, {
				organization:[],
				orgNo:"370404",
				name:"峄城区"
			}, {
				organization:[],
				orgNo:"370405",
				name:"台儿庄区"
			}, {
				organization:[],
				orgNo:"370406",
				name:"山亭区"
			}, {
				organization:[],
				orgNo:"370481",
				name:"滕州市"
			} ],
			orgNo:"3704",
			name:"枣庄市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"370502",
				name:"东营区"
			}, {
				organization:[],
				orgNo:"370503",
				name:"河口区"
			}, {
				organization:[],
				orgNo:"370521",
				name:"垦利县"
			}, {
				organization:[],
				orgNo:"370522",
				name:"利津县"
			}, {
				organization:[],
				orgNo:"370523",
				name:"广饶县"
			} ],
			orgNo:"3705",
			name:"东营市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"370602",
				name:"芝罘区"
			}, {
				organization:[],
				orgNo:"370611",
				name:"福山区"
			}, {
				organization:[],
				orgNo:"370612",
				name:"牟平区"
			}, {
				organization:[],
				orgNo:"370613",
				name:"莱山区"
			}, {
				organization:[],
				orgNo:"370634",
				name:"长岛县"
			}, {
				organization:[],
				orgNo:"370681",
				name:"龙口市"
			}, {
				organization:[],
				orgNo:"370682",
				name:"莱阳市"
			}, {
				organization:[],
				orgNo:"370683",
				name:"莱州市"
			}, {
				organization:[],
				orgNo:"370684",
				name:"蓬莱市"
			}, {
				organization:[],
				orgNo:"370685",
				name:"招远市"
			}, {
				organization:[],
				orgNo:"370686",
				name:"栖霞市"
			}, {
				organization:[],
				orgNo:"370687",
				name:"海阳市"
			} ],
			orgNo:"3706",
			name:"烟台市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"370702",
				name:"潍城区"
			}, {
				organization:[],
				orgNo:"370703",
				name:"寒亭区"
			}, {
				organization:[],
				orgNo:"370704",
				name:"坊子区"
			}, {
				organization:[],
				orgNo:"370705",
				name:"奎文区"
			}, {
				organization:[],
				orgNo:"370724",
				name:"临朐县"
			}, {
				organization:[],
				orgNo:"370725",
				name:"昌乐县"
			}, {
				organization:[],
				orgNo:"370781",
				name:"青州市"
			}, {
				organization:[],
				orgNo:"370782",
				name:"诸城市"
			}, {
				organization:[],
				orgNo:"370783",
				name:"寿光市"
			}, {
				organization:[],
				orgNo:"370784",
				name:"安丘市"
			}, {
				organization:[],
				orgNo:"370785",
				name:"高密市"
			}, {
				organization:[],
				orgNo:"370786",
				name:"昌邑市"
			} ],
			orgNo:"3707",
			name:"潍坊市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"370802",
				name:"市中区"
			}, {
				organization:[],
				orgNo:"370811",
				name:"任城区"
			}, {
				organization:[],
				orgNo:"370826",
				name:"微山县"
			}, {
				organization:[],
				orgNo:"370827",
				name:"鱼台县"
			}, {
				organization:[],
				orgNo:"370828",
				name:"金乡县"
			}, {
				organization:[],
				orgNo:"370829",
				name:"嘉祥县"
			}, {
				organization:[],
				orgNo:"370830",
				name:"汶上县"
			}, {
				organization:[],
				orgNo:"370831",
				name:"泗水县"
			}, {
				organization:[],
				orgNo:"370832",
				name:"梁山县"
			}, {
				organization:[],
				orgNo:"370881",
				name:"曲阜市"
			}, {
				organization:[],
				orgNo:"370882",
				name:"兖州市"
			}, {
				organization:[],
				orgNo:"370883",
				name:"邹城市"
			} ],
			orgNo:"3708",
			name:"济宁市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"370902",
				name:"泰山区"
			}, {
				organization:[],
				orgNo:"370903",
				name:"岱岳区"
			}, {
				organization:[],
				orgNo:"370921",
				name:"宁阳县"
			}, {
				organization:[],
				orgNo:"370923",
				name:"东平县"
			}, {
				organization:[],
				orgNo:"370982",
				name:"新泰市"
			}, {
				organization:[],
				orgNo:"370983",
				name:"肥城市"
			} ],
			orgNo:"3709",
			name:"泰安市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"371002",
				name:"环翠区"
			}, {
				organization:[],
				orgNo:"371081",
				name:"文登市"
			}, {
				organization:[],
				orgNo:"371082",
				name:"荣成市"
			}, {
				organization:[],
				orgNo:"371083",
				name:"乳山市"
			} ],
			orgNo:"3710",
			name:"威海市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"371102",
				name:"东港区"
			}, {
				organization:[],
				orgNo:"371103",
				name:"岚山区"
			}, {
				organization:[],
				orgNo:"371121",
				name:"五莲县"
			}, {
				organization:[],
				orgNo:"371122",
				name:"莒县"
			} ],
			orgNo:"3711",
			name:"日照市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"371202",
				name:"莱城区"
			}, {
				organization:[],
				orgNo:"371203",
				name:"钢城区"
			} ],
			orgNo:"3712",
			name:"莱芜市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"371302",
				name:"兰山区"
			}, {
				organization:[],
				orgNo:"371311",
				name:"罗庄区"
			}, {
				organization:[],
				orgNo:"371312",
				name:"河东区"
			}, {
				organization:[],
				orgNo:"371321",
				name:"沂南县"
			}, {
				organization:[],
				orgNo:"371322",
				name:"郯城县"
			}, {
				organization:[],
				orgNo:"371323",
				name:"沂水县"
			}, {
				organization:[],
				orgNo:"371324",
				name:"苍山县"
			}, {
				organization:[],
				orgNo:"371325",
				name:"费县"
			}, {
				organization:[],
				orgNo:"371326",
				name:"平邑县"
			}, {
				organization:[],
				orgNo:"371327",
				name:"莒南县"
			}, {
				organization:[],
				orgNo:"371328",
				name:"蒙阴县"
			}, {
				organization:[],
				orgNo:"371329",
				name:"临沭县"
			} ],
			orgNo:"3713",
			name:"临沂市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"371402",
				name:"德城区"
			}, {
				organization:[],
				orgNo:"371421",
				name:"陵县"
			}, {
				organization:[],
				orgNo:"371422",
				name:"宁津县"
			}, {
				organization:[],
				orgNo:"371423",
				name:"庆云县"
			}, {
				organization:[],
				orgNo:"371424",
				name:"临邑县"
			}, {
				organization:[],
				orgNo:"371425",
				name:"齐河县"
			}, {
				organization:[],
				orgNo:"371426",
				name:"平原县"
			}, {
				organization:[],
				orgNo:"371427",
				name:"夏津县"
			}, {
				organization:[],
				orgNo:"371428",
				name:"武城县"
			}, {
				organization:[],
				orgNo:"371481",
				name:"乐陵市"
			}, {
				organization:[],
				orgNo:"371482",
				name:"禹城市"
			} ],
			orgNo:"3714",
			name:"德州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"371502",
				name:"东昌府区"
			}, {
				organization:[],
				orgNo:"371521",
				name:"阳谷县"
			}, {
				organization:[],
				orgNo:"371522",
				name:"莘县"
			}, {
				organization:[],
				orgNo:"371523",
				name:"茌平县"
			}, {
				organization:[],
				orgNo:"371524",
				name:"东阿县"
			}, {
				organization:[],
				orgNo:"371525",
				name:"冠县"
			}, {
				organization:[],
				orgNo:"371526",
				name:"高唐县"
			}, {
				organization:[],
				orgNo:"371581",
				name:"临清市"
			} ],
			orgNo:"3715",
			name:"聊城市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"371602",
				name:"滨城区"
			}, {
				organization:[],
				orgNo:"371621",
				name:"惠民县"
			}, {
				organization:[],
				orgNo:"371622",
				name:"阳信县"
			}, {
				organization:[],
				orgNo:"371623",
				name:"无棣县"
			}, {
				organization:[],
				orgNo:"371624",
				name:"沾化县"
			}, {
				organization:[],
				orgNo:"371625",
				name:"博兴县"
			}, {
				organization:[],
				orgNo:"371626",
				name:"邹平县"
			} ],
			orgNo:"3716",
			name:"滨州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"371702",
				name:"牡丹区"
			}, {
				organization:[],
				orgNo:"371721",
				name:"曹县"
			}, {
				organization:[],
				orgNo:"371722",
				name:"单县"
			}, {
				organization:[],
				orgNo:"371723",
				name:"成武县"
			}, {
				organization:[],
				orgNo:"371724",
				name:"巨野县"
			}, {
				organization:[],
				orgNo:"371725",
				name:"郓城县"
			}, {
				organization:[],
				orgNo:"371726",
				name:"鄄城县"
			}, {
				organization:[],
				orgNo:"371727",
				name:"定陶县"
			}, {
				organization:[],
				orgNo:"371728",
				name:"东明县"
			} ],
			orgNo:"3717",
			name:"菏泽市"
		} ],
		orgNo:"37",
		name:"山东"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"410102",
				name:"中原区"
			}, {
				organization:[],
				orgNo:"410103",
				name:"二七区"
			}, {
				organization:[],
				orgNo:"410104",
				name:"管城回族区"
			}, {
				organization:[],
				orgNo:"410105",
				name:"金水区"
			}, {
				organization:[],
				orgNo:"410106",
				name:"上街区"
			}, {
				organization:[],
				orgNo:"410108",
				name:"惠济区"
			}, {
				organization:[],
				orgNo:"410122",
				name:"中牟县"
			}, {
				organization:[],
				orgNo:"410181",
				name:"巩义市"
			}, {
				organization:[],
				orgNo:"410182",
				name:"荥阳市"
			}, {
				organization:[],
				orgNo:"410183",
				name:"新密市"
			}, {
				organization:[],
				orgNo:"410184",
				name:"新郑市"
			}, {
				organization:[],
				orgNo:"410185",
				name:"登封市"
			}, {
				organization:[],
				orgNo:"410186",
				name:"高新区"
			} ],
			orgNo:"4101",
			name:"郑州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"410202",
				name:"龙亭区"
			}, {
				organization:[],
				orgNo:"410203",
				name:"顺河回族区"
			}, {
				organization:[],
				orgNo:"410204",
				name:"鼓楼区"
			}, {
				organization:[],
				orgNo:"410205",
				name:"禹王台区"
			}, {
				organization:[],
				orgNo:"410211",
				name:"金明区"
			}, {
				organization:[],
				orgNo:"410221",
				name:"杞县"
			}, {
				organization:[],
				orgNo:"410222",
				name:"通许县"
			}, {
				organization:[],
				orgNo:"410223",
				name:"尉氏县"
			}, {
				organization:[],
				orgNo:"410224",
				name:"开封县"
			}, {
				organization:[],
				orgNo:"410225",
				name:"兰考县"
			} ],
			orgNo:"4102",
			name:"开封市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"410302",
				name:"老城区"
			}, {
				organization:[],
				orgNo:"410303",
				name:"西工区"
			}, {
				organization:[],
				orgNo:"410304",
				name:"廛河回族区"
			}, {
				organization:[],
				orgNo:"410305",
				name:"涧西区"
			}, {
				organization:[],
				orgNo:"410306",
				name:"吉利区"
			}, {
				organization:[],
				orgNo:"410307",
				name:"洛龙区"
			}, {
				organization:[],
				orgNo:"410322",
				name:"孟津县"
			}, {
				organization:[],
				orgNo:"410323",
				name:"新安县"
			}, {
				organization:[],
				orgNo:"410324",
				name:"栾川县"
			}, {
				organization:[],
				orgNo:"410325",
				name:"嵩县"
			}, {
				organization:[],
				orgNo:"410326",
				name:"汝阳县"
			}, {
				organization:[],
				orgNo:"410327",
				name:"宜阳县"
			}, {
				organization:[],
				orgNo:"410328",
				name:"洛宁县"
			}, {
				organization:[],
				orgNo:"410329",
				name:"伊川县"
			}, {
				organization:[],
				orgNo:"410381",
				name:"偃师市"
			} ],
			orgNo:"4103",
			name:"洛阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"410402",
				name:"新华区"
			}, {
				organization:[],
				orgNo:"410403",
				name:"卫东区"
			}, {
				organization:[],
				orgNo:"410404",
				name:"石龙区"
			}, {
				organization:[],
				orgNo:"410411",
				name:"湛河区"
			}, {
				organization:[],
				orgNo:"410421",
				name:"宝丰县"
			}, {
				organization:[],
				orgNo:"410422",
				name:"叶县"
			}, {
				organization:[],
				orgNo:"410423",
				name:"鲁山县"
			}, {
				organization:[],
				orgNo:"410425",
				name:"郏县"
			}, {
				organization:[],
				orgNo:"410481",
				name:"舞钢市"
			}, {
				organization:[],
				orgNo:"410482",
				name:"汝州市"
			} ],
			orgNo:"4104",
			name:"平顶山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"410502",
				name:"文峰区"
			}, {
				organization:[],
				orgNo:"410503",
				name:"北关区"
			}, {
				organization:[],
				orgNo:"410505",
				name:"殷都区"
			}, {
				organization:[],
				orgNo:"410506",
				name:"龙安区"
			}, {
				organization:[],
				orgNo:"410522",
				name:"安阳县"
			}, {
				organization:[],
				orgNo:"410523",
				name:"汤阴县"
			}, {
				organization:[],
				orgNo:"410526",
				name:"滑县"
			}, {
				organization:[],
				orgNo:"410527",
				name:"内黄县"
			}, {
				organization:[],
				orgNo:"410581",
				name:"林州市"
			} ],
			orgNo:"4105",
			name:"安阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"410602",
				name:"鹤山区"
			}, {
				organization:[],
				orgNo:"410603",
				name:"山城区"
			}, {
				organization:[],
				orgNo:"410611",
				name:"淇滨区"
			}, {
				organization:[],
				orgNo:"410621",
				name:"浚县"
			}, {
				organization:[],
				orgNo:"410622",
				name:"淇县"
			} ],
			orgNo:"4106",
			name:"鹤壁市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"410702",
				name:"红旗区"
			}, {
				organization:[],
				orgNo:"410703",
				name:"卫滨区"
			}, {
				organization:[],
				orgNo:"410704",
				name:"凤泉区"
			}, {
				organization:[],
				orgNo:"410711",
				name:"牧野区"
			}, {
				organization:[],
				orgNo:"410721",
				name:"新乡县"
			}, {
				organization:[],
				orgNo:"410724",
				name:"获嘉县"
			}, {
				organization:[],
				orgNo:"410725",
				name:"原阳县"
			}, {
				organization:[],
				orgNo:"410726",
				name:"延津县"
			}, {
				organization:[],
				orgNo:"410727",
				name:"封丘县"
			}, {
				organization:[],
				orgNo:"410728",
				name:"长垣县"
			}, {
				organization:[],
				orgNo:"410781",
				name:"卫辉市"
			}, {
				organization:[],
				orgNo:"410782",
				name:"辉县市"
			} ],
			orgNo:"4107",
			name:"新乡市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"410802",
				name:"解放区"
			}, {
				organization:[],
				orgNo:"410803",
				name:"中站区"
			}, {
				organization:[],
				orgNo:"410804",
				name:"马村区"
			}, {
				organization:[],
				orgNo:"410811",
				name:"山阳区"
			}, {
				organization:[],
				orgNo:"410821",
				name:"修武县"
			}, {
				organization:[],
				orgNo:"410822",
				name:"博爱县"
			}, {
				organization:[],
				orgNo:"410823",
				name:"武陟县"
			}, {
				organization:[],
				orgNo:"410825",
				name:"温县"
			}, {
				organization:[],
				orgNo:"410882",
				name:"沁阳市"
			}, {
				organization:[],
				orgNo:"410883",
				name:"孟州市"
			} ],
			orgNo:"4108",
			name:"焦作市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"410902",
				name:"华龙区"
			}, {
				organization:[],
				orgNo:"410922",
				name:"清丰县"
			}, {
				organization:[],
				orgNo:"410923",
				name:"南乐县"
			}, {
				organization:[],
				orgNo:"410926",
				name:"范县"
			}, {
				organization:[],
				orgNo:"410927",
				name:"台前县"
			}, {
				organization:[],
				orgNo:"410928",
				name:"濮阳县"
			} ],
			orgNo:"4109",
			name:"濮阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"411002",
				name:"魏都区"
			}, {
				organization:[],
				orgNo:"411023",
				name:"许昌县"
			}, {
				organization:[],
				orgNo:"411024",
				name:"鄢陵县"
			}, {
				organization:[],
				orgNo:"411025",
				name:"襄城县"
			}, {
				organization:[],
				orgNo:"411081",
				name:"禹州市"
			}, {
				organization:[],
				orgNo:"411082",
				name:"长葛市"
			} ],
			orgNo:"4110",
			name:"许昌市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"411102",
				name:"源汇区"
			}, {
				organization:[],
				orgNo:"411103",
				name:"郾城区"
			}, {
				organization:[],
				orgNo:"411104",
				name:"召陵区"
			}, {
				organization:[],
				orgNo:"411121",
				name:"舞阳县"
			}, {
				organization:[],
				orgNo:"411122",
				name:"临颍县"
			} ],
			orgNo:"4111",
			name:"漯河市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"411202",
				name:"湖滨区"
			}, {
				organization:[],
				orgNo:"411221",
				name:"渑池县"
			}, {
				organization:[],
				orgNo:"411222",
				name:"陕县"
			}, {
				organization:[],
				orgNo:"411224",
				name:"卢氏县"
			}, {
				organization:[],
				orgNo:"411281",
				name:"义马市"
			}, {
				organization:[],
				orgNo:"411282",
				name:"灵宝市"
			} ],
			orgNo:"4112",
			name:"三门峡市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"411302",
				name:"宛城区"
			}, {
				organization:[],
				orgNo:"411303",
				name:"卧龙区"
			}, {
				organization:[],
				orgNo:"411321",
				name:"南召县"
			}, {
				organization:[],
				orgNo:"411322",
				name:"方城县"
			}, {
				organization:[],
				orgNo:"411323",
				name:"西峡县"
			}, {
				organization:[],
				orgNo:"411324",
				name:"镇平县"
			}, {
				organization:[],
				orgNo:"411325",
				name:"内乡县"
			}, {
				organization:[],
				orgNo:"411326",
				name:"淅川县"
			}, {
				organization:[],
				orgNo:"411327",
				name:"社旗县"
			}, {
				organization:[],
				orgNo:"411328",
				name:"唐河县"
			}, {
				organization:[],
				orgNo:"411329",
				name:"新野县"
			}, {
				organization:[],
				orgNo:"411330",
				name:"桐柏县"
			}, {
				organization:[],
				orgNo:"411381",
				name:"邓州市"
			} ],
			orgNo:"4113",
			name:"南阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"411402",
				name:"梁园区"
			}, {
				organization:[],
				orgNo:"411403",
				name:"睢阳区"
			}, {
				organization:[],
				orgNo:"411421",
				name:"民权县"
			}, {
				organization:[],
				orgNo:"411422",
				name:"睢县"
			}, {
				organization:[],
				orgNo:"411423",
				name:"宁陵县"
			}, {
				organization:[],
				orgNo:"411424",
				name:"柘城县"
			}, {
				organization:[],
				orgNo:"411425",
				name:"虞城县"
			}, {
				organization:[],
				orgNo:"411426",
				name:"夏邑县"
			}, {
				organization:[],
				orgNo:"411481",
				name:"永城市"
			} ],
			orgNo:"4114",
			name:"商丘市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"411502",
				name:"浉河区"
			}, {
				organization:[],
				orgNo:"411503",
				name:"平桥区"
			}, {
				organization:[],
				orgNo:"411521",
				name:"罗山县"
			}, {
				organization:[],
				orgNo:"411522",
				name:"光山县"
			}, {
				organization:[],
				orgNo:"411523",
				name:"新县"
			}, {
				organization:[],
				orgNo:"411524",
				name:"商城县"
			}, {
				organization:[],
				orgNo:"411525",
				name:"固始县"
			}, {
				organization:[],
				orgNo:"411526",
				name:"潢川县"
			}, {
				organization:[],
				orgNo:"411527",
				name:"淮滨县"
			}, {
				organization:[],
				orgNo:"411528",
				name:"息县"
			} ],
			orgNo:"4115",
			name:"信阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"411602",
				name:"川汇区"
			}, {
				organization:[],
				orgNo:"411621",
				name:"扶沟县"
			}, {
				organization:[],
				orgNo:"411622",
				name:"西华县"
			}, {
				organization:[],
				orgNo:"411623",
				name:"商水县"
			}, {
				organization:[],
				orgNo:"411624",
				name:"沈丘县"
			}, {
				organization:[],
				orgNo:"411625",
				name:"郸城县"
			}, {
				organization:[],
				orgNo:"411626",
				name:"淮阳县"
			}, {
				organization:[],
				orgNo:"411627",
				name:"太康县"
			}, {
				organization:[],
				orgNo:"411628",
				name:"鹿邑县"
			}, {
				organization:[],
				orgNo:"411681",
				name:"项城市"
			} ],
			orgNo:"4116",
			name:"周口市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"411702",
				name:"驿城区"
			}, {
				organization:[],
				orgNo:"411721",
				name:"西平县"
			}, {
				organization:[],
				orgNo:"411722",
				name:"上蔡县"
			}, {
				organization:[],
				orgNo:"411723",
				name:"平舆县"
			}, {
				organization:[],
				orgNo:"411724",
				name:"正阳县"
			}, {
				organization:[],
				orgNo:"411725",
				name:"确山县"
			}, {
				organization:[],
				orgNo:"411726",
				name:"泌阳县"
			}, {
				organization:[],
				orgNo:"411727",
				name:"汝南县"
			}, {
				organization:[],
				orgNo:"411728",
				name:"遂平县"
			}, {
				organization:[],
				orgNo:"411729",
				name:"新蔡县"
			} ],
			orgNo:"4117",
			name:"驻马店市"
		}, {
			organization:[],
			orgNo:"4118",
			name:"济源市"
		} ],
		orgNo:"41",
		name:"河南"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"420102",
				name:"江岸区"
			}, {
				organization:[],
				orgNo:"420103",
				name:"江汉区"
			}, {
				organization:[],
				orgNo:"420104",
				name:"硚口区"
			}, {
				organization:[],
				orgNo:"420105",
				name:"汉阳区"
			}, {
				organization:[],
				orgNo:"420106",
				name:"武昌区"
			}, {
				organization:[],
				orgNo:"420107",
				name:"青山区"
			}, {
				organization:[],
				orgNo:"420111",
				name:"洪山区"
			}, {
				organization:[],
				orgNo:"420112",
				name:"东西湖区"
			}, {
				organization:[],
				orgNo:"420113",
				name:"汉南区"
			}, {
				organization:[],
				orgNo:"420114",
				name:"蔡甸区"
			}, {
				organization:[],
				orgNo:"420115",
				name:"江夏区"
			}, {
				organization:[],
				orgNo:"420116",
				name:"黄陂区"
			}, {
				organization:[],
				orgNo:"420117",
				name:"新洲区"
			} ],
			orgNo:"4201",
			name:"武汉市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"420202",
				name:"黄石港区"
			}, {
				organization:[],
				orgNo:"420203",
				name:"西塞山区"
			}, {
				organization:[],
				orgNo:"420204",
				name:"下陆区"
			}, {
				organization:[],
				orgNo:"420205",
				name:"铁山区"
			}, {
				organization:[],
				orgNo:"420222",
				name:"阳新县"
			}, {
				organization:[],
				orgNo:"420281",
				name:"大冶市"
			} ],
			orgNo:"4202",
			name:"黄石市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"420302",
				name:"茅箭区"
			}, {
				organization:[],
				orgNo:"420303",
				name:"张湾区"
			}, {
				organization:[],
				orgNo:"420321",
				name:"郧县"
			}, {
				organization:[],
				orgNo:"420322",
				name:"郧西县"
			}, {
				organization:[],
				orgNo:"420323",
				name:"竹山县"
			}, {
				organization:[],
				orgNo:"420324",
				name:"竹溪县"
			}, {
				organization:[],
				orgNo:"420325",
				name:"房县"
			}, {
				organization:[],
				orgNo:"420381",
				name:"丹江口市"
			} ],
			orgNo:"4203",
			name:"十堰市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"420502",
				name:"西陵区"
			}, {
				organization:[],
				orgNo:"420503",
				name:"伍家岗区"
			}, {
				organization:[],
				orgNo:"420504",
				name:"点军区"
			}, {
				organization:[],
				orgNo:"420505",
				name:"猇亭区"
			}, {
				organization:[],
				orgNo:"420506",
				name:"夷陵区"
			}, {
				organization:[],
				orgNo:"420525",
				name:"远安县"
			}, {
				organization:[],
				orgNo:"420526",
				name:"兴山县"
			}, {
				organization:[],
				orgNo:"420527",
				name:"秭归县"
			}, {
				organization:[],
				orgNo:"420528",
				name:"长阳土家族自治县"
			}, {
				organization:[],
				orgNo:"420529",
				name:"五峰土家族自治县"
			}, {
				organization:[],
				orgNo:"420581",
				name:"宜都市"
			}, {
				organization:[],
				orgNo:"420582",
				name:"当阳市"
			}, {
				organization:[],
				orgNo:"420583",
				name:"枝江市"
			} ],
			orgNo:"4205",
			name:"宜昌市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"420602",
				name:"襄城区"
			}, {
				organization:[],
				orgNo:"420606",
				name:"樊城区"
			}, {
				organization:[],
				orgNo:"420607",
				name:"襄阳区"
			}, {
				organization:[],
				orgNo:"420624",
				name:"南漳县"
			}, {
				organization:[],
				orgNo:"420625",
				name:"谷城县"
			}, {
				organization:[],
				orgNo:"420626",
				name:"保康县"
			}, {
				organization:[],
				orgNo:"420682",
				name:"老河口市"
			}, {
				organization:[],
				orgNo:"420683",
				name:"枣阳市"
			}, {
				organization:[],
				orgNo:"420684",
				name:"宜城市"
			} ],
			orgNo:"4206",
			name:"襄樊市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"420702",
				name:"梁子湖区"
			}, {
				organization:[],
				orgNo:"420703",
				name:"华容区"
			}, {
				organization:[],
				orgNo:"420704",
				name:"鄂城区"
			} ],
			orgNo:"4207",
			name:"鄂州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"420802",
				name:"东宝区"
			}, {
				organization:[],
				orgNo:"420804",
				name:"掇刀区"
			}, {
				organization:[],
				orgNo:"420821",
				name:"京山县"
			}, {
				organization:[],
				orgNo:"420822",
				name:"沙洋县"
			}, {
				organization:[],
				orgNo:"420881",
				name:"钟祥市"
			} ],
			orgNo:"4208",
			name:"荆门市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"420902",
				name:"孝南区"
			}, {
				organization:[],
				orgNo:"420921",
				name:"孝昌县"
			}, {
				organization:[],
				orgNo:"420922",
				name:"大悟县"
			}, {
				organization:[],
				orgNo:"420923",
				name:"云梦县"
			}, {
				organization:[],
				orgNo:"420981",
				name:"应城市"
			}, {
				organization:[],
				orgNo:"420982",
				name:"安陆市"
			}, {
				organization:[],
				orgNo:"420984",
				name:"汉川市"
			} ],
			orgNo:"4209",
			name:"孝感市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"421002",
				name:"沙市区"
			}, {
				organization:[],
				orgNo:"421003",
				name:"荆州区"
			}, {
				organization:[],
				orgNo:"421022",
				name:"公安县"
			}, {
				organization:[],
				orgNo:"421023",
				name:"监利县"
			}, {
				organization:[],
				orgNo:"421024",
				name:"江陵县"
			}, {
				organization:[],
				orgNo:"421081",
				name:"石首市"
			}, {
				organization:[],
				orgNo:"421083",
				name:"洪湖市"
			}, {
				organization:[],
				orgNo:"421087",
				name:"松滋市"
			} ],
			orgNo:"4210",
			name:"荆州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"421102",
				name:"黄州区"
			}, {
				organization:[],
				orgNo:"421121",
				name:"团风县"
			}, {
				organization:[],
				orgNo:"421122",
				name:"红安县"
			}, {
				organization:[],
				orgNo:"421123",
				name:"罗田县"
			}, {
				organization:[],
				orgNo:"421124",
				name:"英山县"
			}, {
				organization:[],
				orgNo:"421125",
				name:"浠水县"
			}, {
				organization:[],
				orgNo:"421126",
				name:"蕲春县"
			}, {
				organization:[],
				orgNo:"421127",
				name:"黄梅县"
			}, {
				organization:[],
				orgNo:"421181",
				name:"麻城市"
			}, {
				organization:[],
				orgNo:"421182",
				name:"武穴市"
			} ],
			orgNo:"4211",
			name:"黄冈市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"421202",
				name:"咸安区"
			}, {
				organization:[],
				orgNo:"421221",
				name:"嘉鱼县"
			}, {
				organization:[],
				orgNo:"421222",
				name:"通城县"
			}, {
				organization:[],
				orgNo:"421223",
				name:"崇阳县"
			}, {
				organization:[],
				orgNo:"421224",
				name:"通山县"
			}, {
				organization:[],
				orgNo:"421281",
				name:"赤壁市"
			} ],
			orgNo:"4212",
			name:"咸宁市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"421302",
				name:"曾都区"
			}, {
				organization:[],
				orgNo:"421381",
				name:"广水市"
			} ],
			orgNo:"4213",
			name:"随州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"422801",
				name:"恩施市"
			}, {
				organization:[],
				orgNo:"422802",
				name:"利川市"
			}, {
				organization:[],
				orgNo:"422822",
				name:"建始县"
			}, {
				organization:[],
				orgNo:"422823",
				name:"巴东县"
			}, {
				organization:[],
				orgNo:"422825",
				name:"宣恩县"
			}, {
				organization:[],
				orgNo:"422826",
				name:"咸丰县"
			}, {
				organization:[],
				orgNo:"422827",
				name:"来凤县"
			}, {
				organization:[],
				orgNo:"422828",
				name:"鹤峰县"
			} ],
			orgNo:"4228",
			name:"恩施土家族苗族自治州"
		}, {
			organization:[],
			orgNo:"429004",
			name:"仙桃市"
		}, {
			organization:[],
			orgNo:"429005",
			name:"潜江市"
		}, {
			organization:[],
			orgNo:"429006",
			name:"天门市"
		}, {
			organization:[],
			orgNo:"429021",
			name:"神农架林区"
		} ],
		orgNo:"42",
		name:"湖北"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"430102",
				name:"芙蓉区"
			}, {
				organization:[],
				orgNo:"430103",
				name:"天心区"
			}, {
				organization:[],
				orgNo:"430104",
				name:"岳麓区"
			}, {
				organization:[],
				orgNo:"430105",
				name:"开福区"
			}, {
				organization:[],
				orgNo:"430111",
				name:"雨花区"
			}, {
				organization:[],
				orgNo:"430121",
				name:"长沙县"
			}, {
				organization:[],
				orgNo:"430122",
				name:"望城县"
			}, {
				organization:[],
				orgNo:"430124",
				name:"宁乡县"
			}, {
				organization:[],
				orgNo:"430181",
				name:"浏阳市"
			} ],
			orgNo:"4301",
			name:"长沙市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"430202",
				name:"荷塘区"
			}, {
				organization:[],
				orgNo:"430203",
				name:"芦淞区"
			}, {
				organization:[],
				orgNo:"430204",
				name:"石峰区"
			}, {
				organization:[],
				orgNo:"430211",
				name:"天元区"
			}, {
				organization:[],
				orgNo:"430221",
				name:"株洲县"
			}, {
				organization:[],
				orgNo:"430223",
				name:"攸县"
			}, {
				organization:[],
				orgNo:"430224",
				name:"茶陵县"
			}, {
				organization:[],
				orgNo:"430225",
				name:"炎陵县"
			}, {
				organization:[],
				orgNo:"430281",
				name:"醴陵市"
			} ],
			orgNo:"4302",
			name:"株洲市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"430302",
				name:"雨湖区"
			}, {
				organization:[],
				orgNo:"430304",
				name:"岳塘区"
			}, {
				organization:[],
				orgNo:"430321",
				name:"湘潭县"
			}, {
				organization:[],
				orgNo:"430381",
				name:"湘乡市"
			}, {
				organization:[],
				orgNo:"430382",
				name:"韶山市"
			} ],
			orgNo:"4303",
			name:"湘潭市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"430405",
				name:"珠晖区"
			}, {
				organization:[],
				orgNo:"430406",
				name:"雁峰区"
			}, {
				organization:[],
				orgNo:"430407",
				name:"石鼓区"
			}, {
				organization:[],
				orgNo:"430408",
				name:"蒸湘区"
			}, {
				organization:[],
				orgNo:"430412",
				name:"南岳区"
			}, {
				organization:[],
				orgNo:"430421",
				name:"衡阳县"
			}, {
				organization:[],
				orgNo:"430422",
				name:"衡南县"
			}, {
				organization:[],
				orgNo:"430423",
				name:"衡山县"
			}, {
				organization:[],
				orgNo:"430424",
				name:"衡东县"
			}, {
				organization:[],
				orgNo:"430426",
				name:"祁东县"
			}, {
				organization:[],
				orgNo:"430481",
				name:"耒阳市"
			}, {
				organization:[],
				orgNo:"430482",
				name:"常宁市"
			} ],
			orgNo:"4304",
			name:"衡阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"430502",
				name:"双清区"
			}, {
				organization:[],
				orgNo:"430503",
				name:"大祥区"
			}, {
				organization:[],
				orgNo:"430511",
				name:"北塔区"
			}, {
				organization:[],
				orgNo:"430521",
				name:"邵东县"
			}, {
				organization:[],
				orgNo:"430522",
				name:"新邵县"
			}, {
				organization:[],
				orgNo:"430523",
				name:"邵阳县"
			}, {
				organization:[],
				orgNo:"430524",
				name:"隆回县"
			}, {
				organization:[],
				orgNo:"430525",
				name:"洞口县"
			}, {
				organization:[],
				orgNo:"430527",
				name:"绥宁县"
			}, {
				organization:[],
				orgNo:"430528",
				name:"新宁县"
			}, {
				organization:[],
				orgNo:"430529",
				name:"城步苗族自治县"
			}, {
				organization:[],
				orgNo:"430581",
				name:"武冈市"
			} ],
			orgNo:"4305",
			name:"邵阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"430602",
				name:"岳阳楼区"
			}, {
				organization:[],
				orgNo:"430603",
				name:"云溪区"
			}, {
				organization:[],
				orgNo:"430611",
				name:"君山区"
			}, {
				organization:[],
				orgNo:"430621",
				name:"岳阳县"
			}, {
				organization:[],
				orgNo:"430623",
				name:"华容县"
			}, {
				organization:[],
				orgNo:"430624",
				name:"湘阴县"
			}, {
				organization:[],
				orgNo:"430626",
				name:"平江县"
			}, {
				organization:[],
				orgNo:"430681",
				name:"汨罗市"
			}, {
				organization:[],
				orgNo:"430682",
				name:"临湘市"
			} ],
			orgNo:"4306",
			name:"岳阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"430702",
				name:"武陵区"
			}, {
				organization:[],
				orgNo:"430703",
				name:"鼎城区"
			}, {
				organization:[],
				orgNo:"430721",
				name:"安乡县"
			}, {
				organization:[],
				orgNo:"430722",
				name:"汉寿县"
			}, {
				organization:[],
				orgNo:"430723",
				name:"澧县"
			}, {
				organization:[],
				orgNo:"430724",
				name:"临澧县"
			}, {
				organization:[],
				orgNo:"430725",
				name:"桃源县"
			}, {
				organization:[],
				orgNo:"430726",
				name:"石门县"
			}, {
				organization:[],
				orgNo:"430781",
				name:"津市市"
			} ],
			orgNo:"4307",
			name:"常德市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"430802",
				name:"永定区"
			}, {
				organization:[],
				orgNo:"430811",
				name:"武陵源区"
			}, {
				organization:[],
				orgNo:"430821",
				name:"慈利县"
			}, {
				organization:[],
				orgNo:"430822",
				name:"桑植县"
			} ],
			orgNo:"4308",
			name:"张家界市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"430902",
				name:"资阳区"
			}, {
				organization:[],
				orgNo:"430903",
				name:"赫山区"
			}, {
				organization:[],
				orgNo:"430921",
				name:"南县"
			}, {
				organization:[],
				orgNo:"430922",
				name:"桃江县"
			}, {
				organization:[],
				orgNo:"430923",
				name:"安化县"
			}, {
				organization:[],
				orgNo:"430981",
				name:"沅江市"
			} ],
			orgNo:"4309",
			name:"益阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"431002",
				name:"北湖区"
			}, {
				organization:[],
				orgNo:"431003",
				name:"苏仙区"
			}, {
				organization:[],
				orgNo:"431021",
				name:"桂阳县"
			}, {
				organization:[],
				orgNo:"431022",
				name:"宜章县"
			}, {
				organization:[],
				orgNo:"431023",
				name:"永兴县"
			}, {
				organization:[],
				orgNo:"431024",
				name:"嘉禾县"
			}, {
				organization:[],
				orgNo:"431025",
				name:"临武县"
			}, {
				organization:[],
				orgNo:"431026",
				name:"汝城县"
			}, {
				organization:[],
				orgNo:"431027",
				name:"桂东县"
			}, {
				organization:[],
				orgNo:"431028",
				name:"安仁县"
			}, {
				organization:[],
				orgNo:"431081",
				name:"资兴市"
			} ],
			orgNo:"4310",
			name:"郴州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"431102",
				name:"零陵区"
			}, {
				organization:[],
				orgNo:"431103",
				name:"冷水滩区"
			}, {
				organization:[],
				orgNo:"431121",
				name:"祁阳县"
			}, {
				organization:[],
				orgNo:"431122",
				name:"东安县"
			}, {
				organization:[],
				orgNo:"431123",
				name:"双牌县"
			}, {
				organization:[],
				orgNo:"431124",
				name:"道县"
			}, {
				organization:[],
				orgNo:"431125",
				name:"江永县"
			}, {
				organization:[],
				orgNo:"431126",
				name:"宁远县"
			}, {
				organization:[],
				orgNo:"431127",
				name:"蓝山县"
			}, {
				organization:[],
				orgNo:"431128",
				name:"新田县"
			}, {
				organization:[],
				orgNo:"431129",
				name:"江华瑶族自治县"
			}, {
				organization:[],
				orgNo:"431130",
				name:"江华县"
			} ],
			orgNo:"4311",
			name:"永州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"431202",
				name:"鹤城区"
			}, {
				organization:[],
				orgNo:"431221",
				name:"中方县"
			}, {
				organization:[],
				orgNo:"431222",
				name:"沅陵县"
			}, {
				organization:[],
				orgNo:"431223",
				name:"辰溪县"
			}, {
				organization:[],
				orgNo:"431224",
				name:"溆浦县"
			}, {
				organization:[],
				orgNo:"431225",
				name:"会同县"
			}, {
				organization:[],
				orgNo:"431226",
				name:"麻阳苗族自治县"
			}, {
				organization:[],
				orgNo:"431227",
				name:"新晃侗族自治县"
			}, {
				organization:[],
				orgNo:"431228",
				name:"芷江侗族自治县"
			}, {
				organization:[],
				orgNo:"431229",
				name:"靖州苗族侗族自治县"
			}, {
				organization:[],
				orgNo:"431230",
				name:"通道侗族自治县"
			}, {
				organization:[],
				orgNo:"431281",
				name:"洪江市"
			} ],
			orgNo:"4312",
			name:"怀化市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"431302",
				name:"娄星区"
			}, {
				organization:[],
				orgNo:"431321",
				name:"双峰县"
			}, {
				organization:[],
				orgNo:"431322",
				name:"新化县"
			}, {
				organization:[],
				orgNo:"431381",
				name:"冷水江市"
			}, {
				organization:[],
				orgNo:"431382",
				name:"涟源市"
			} ],
			orgNo:"4313",
			name:"娄底市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"433101",
				name:"吉首市"
			}, {
				organization:[],
				orgNo:"433122",
				name:"泸溪县"
			}, {
				organization:[],
				orgNo:"433123",
				name:"凤凰县"
			}, {
				organization:[],
				orgNo:"433124",
				name:"花垣县"
			}, {
				organization:[],
				orgNo:"433125",
				name:"保靖县"
			}, {
				organization:[],
				orgNo:"433126",
				name:"古丈县"
			}, {
				organization:[],
				orgNo:"433127",
				name:"永顺县"
			}, {
				organization:[],
				orgNo:"433130",
				name:"龙山县"
			} ],
			orgNo:"4331",
			name:"湘西土家族苗族自治州"
		} ],
		orgNo:"43",
		name:"湖南"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"440103",
				name:"荔湾区"
			}, {
				organization:[],
				orgNo:"440104",
				name:"越秀区"
			}, {
				organization:[],
				orgNo:"440105",
				name:"海珠区"
			}, {
				organization:[],
				orgNo:"440106",
				name:"天河区"
			}, {
				organization:[],
				orgNo:"440111",
				name:"白云区"
			}, {
				organization:[],
				orgNo:"440112",
				name:"黄埔区"
			}, {
				organization:[],
				orgNo:"440113",
				name:"番禺区"
			}, {
				organization:[],
				orgNo:"440114",
				name:"花都区"
			}, {
				organization:[],
				orgNo:"440115",
				name:"南沙区"
			}, {
				organization:[],
				orgNo:"440116",
				name:"萝岗区"
			}, {
				organization:[],
				orgNo:"440117",
				name:"黄浦区"
			}, {
				organization:[],
				orgNo:"440183",
				name:"增城市"
			}, {
				organization:[],
				orgNo:"440184",
				name:"从化市"
			} ],
			orgNo:"4401",
			name:"广州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"440203",
				name:"武江区"
			}, {
				organization:[],
				orgNo:"440204",
				name:"浈江区"
			}, {
				organization:[],
				orgNo:"440205",
				name:"曲江区"
			}, {
				organization:[],
				orgNo:"440222",
				name:"始兴县"
			}, {
				organization:[],
				orgNo:"440224",
				name:"仁化县"
			}, {
				organization:[],
				orgNo:"440229",
				name:"翁源县"
			}, {
				organization:[],
				orgNo:"440232",
				name:"乳源瑶族自治县"
			}, {
				organization:[],
				orgNo:"440233",
				name:"新丰县"
			}, {
				organization:[],
				orgNo:"440281",
				name:"乐昌市"
			}, {
				organization:[],
				orgNo:"440282",
				name:"南雄市"
			} ],
			orgNo:"4402",
			name:"韶关市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"440303",
				name:"罗湖区"
			}, {
				organization:[],
				orgNo:"440304",
				name:"福田区"
			}, {
				organization:[],
				orgNo:"440305",
				name:"南山区"
			}, {
				organization:[],
				orgNo:"440306",
				name:"宝安区"
			}, {
				organization:[],
				orgNo:"440307",
				name:"龙岗区"
			}, {
				organization:[],
				orgNo:"440308",
				name:"盐田区"
			} ],
			orgNo:"4403",
			name:"深圳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"440402",
				name:"香洲区"
			}, {
				organization:[],
				orgNo:"440403",
				name:"斗门区"
			}, {
				organization:[],
				orgNo:"440404",
				name:"金湾区"
			} ],
			orgNo:"4404",
			name:"珠海市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"440507",
				name:"龙湖区"
			}, {
				organization:[],
				orgNo:"440511",
				name:"金平区"
			}, {
				organization:[],
				orgNo:"440512",
				name:"濠江区"
			}, {
				organization:[],
				orgNo:"440513",
				name:"潮阳区"
			}, {
				organization:[],
				orgNo:"440514",
				name:"潮南区"
			}, {
				organization:[],
				orgNo:"440515",
				name:"澄海区"
			}, {
				organization:[],
				orgNo:"440523",
				name:"南澳县"
			} ],
			orgNo:"4405",
			name:"汕头市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"440604",
				name:"禅城区"
			}, {
				organization:[],
				orgNo:"440605",
				name:"南海区"
			}, {
				organization:[],
				orgNo:"440606",
				name:"顺德区"
			}, {
				organization:[],
				orgNo:"440607",
				name:"三水区"
			}, {
				organization:[],
				orgNo:"440608",
				name:"高明区"
			} ],
			orgNo:"4406",
			name:"佛山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"440703",
				name:"蓬江区"
			}, {
				organization:[],
				orgNo:"440704",
				name:"江海区"
			}, {
				organization:[],
				orgNo:"440705",
				name:"新会区"
			}, {
				organization:[],
				orgNo:"440781",
				name:"台山市"
			}, {
				organization:[],
				orgNo:"440783",
				name:"开平市"
			}, {
				organization:[],
				orgNo:"440784",
				name:"鹤山市"
			}, {
				organization:[],
				orgNo:"440785",
				name:"恩平市"
			} ],
			orgNo:"4407",
			name:"江门市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"440802",
				name:"赤坎区"
			}, {
				organization:[],
				orgNo:"440803",
				name:"霞山区"
			}, {
				organization:[],
				orgNo:"440804",
				name:"坡头区"
			}, {
				organization:[],
				orgNo:"440811",
				name:"麻章区"
			}, {
				organization:[],
				orgNo:"440823",
				name:"遂溪县"
			}, {
				organization:[],
				orgNo:"440825",
				name:"徐闻县"
			}, {
				organization:[],
				orgNo:"440881",
				name:"廉江市"
			}, {
				organization:[],
				orgNo:"440882",
				name:"雷州市"
			}, {
				organization:[],
				orgNo:"440883",
				name:"吴川市"
			} ],
			orgNo:"4408",
			name:"湛江市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"440902",
				name:"茂南区"
			}, {
				organization:[],
				orgNo:"440903",
				name:"茂港区"
			}, {
				organization:[],
				orgNo:"440923",
				name:"电白县"
			}, {
				organization:[],
				orgNo:"440981",
				name:"高州市"
			}, {
				organization:[],
				orgNo:"440982",
				name:"化州市"
			}, {
				organization:[],
				orgNo:"440983",
				name:"信宜市"
			} ],
			orgNo:"4409",
			name:"茂名市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"441202",
				name:"端州区"
			}, {
				organization:[],
				orgNo:"441203",
				name:"鼎湖区"
			}, {
				organization:[],
				orgNo:"441223",
				name:"广宁县"
			}, {
				organization:[],
				orgNo:"441224",
				name:"怀集县"
			}, {
				organization:[],
				orgNo:"441225",
				name:"封开县"
			}, {
				organization:[],
				orgNo:"441226",
				name:"德庆县"
			}, {
				organization:[],
				orgNo:"441283",
				name:"高要市"
			}, {
				organization:[],
				orgNo:"441284",
				name:"四会市"
			} ],
			orgNo:"4412",
			name:"肇庆市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"441302",
				name:"惠城区"
			}, {
				organization:[],
				orgNo:"441303",
				name:"惠阳区"
			}, {
				organization:[],
				orgNo:"441322",
				name:"博罗县"
			}, {
				organization:[],
				orgNo:"441323",
				name:"惠东县"
			}, {
				organization:[],
				orgNo:"441324",
				name:"龙门县"
			} ],
			orgNo:"4413",
			name:"惠州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"441402",
				name:"梅江区"
			}, {
				organization:[],
				orgNo:"441421",
				name:"梅县"
			}, {
				organization:[],
				orgNo:"441422",
				name:"大埔县"
			}, {
				organization:[],
				orgNo:"441423",
				name:"丰顺县"
			}, {
				organization:[],
				orgNo:"441424",
				name:"五华县"
			}, {
				organization:[],
				orgNo:"441426",
				name:"平远县"
			}, {
				organization:[],
				orgNo:"441427",
				name:"蕉岭县"
			}, {
				organization:[],
				orgNo:"441481",
				name:"兴宁市"
			} ],
			orgNo:"4414",
			name:"梅州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"441502",
				name:"城区"
			}, {
				organization:[],
				orgNo:"441521",
				name:"海丰县"
			}, {
				organization:[],
				orgNo:"441523",
				name:"陆河县"
			}, {
				organization:[],
				orgNo:"441581",
				name:"陆丰市"
			} ],
			orgNo:"4415",
			name:"汕尾市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"441602",
				name:"源城区"
			}, {
				organization:[],
				orgNo:"441621",
				name:"紫金县"
			}, {
				organization:[],
				orgNo:"441622",
				name:"龙川县"
			}, {
				organization:[],
				orgNo:"441623",
				name:"连平县"
			}, {
				organization:[],
				orgNo:"441624",
				name:"和平县"
			}, {
				organization:[],
				orgNo:"441625",
				name:"东源县"
			} ],
			orgNo:"4416",
			name:"河源市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"441702",
				name:"江城区"
			}, {
				organization:[],
				orgNo:"441721",
				name:"阳西县"
			}, {
				organization:[],
				orgNo:"441723",
				name:"阳东县"
			}, {
				organization:[],
				orgNo:"441781",
				name:"阳春市"
			} ],
			orgNo:"4417",
			name:"阳江市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"441802",
				name:"清城区"
			}, {
				organization:[],
				orgNo:"441821",
				name:"佛冈县"
			}, {
				organization:[],
				orgNo:"441823",
				name:"阳山县"
			}, {
				organization:[],
				orgNo:"441825",
				name:"连山壮族瑶族自治县"
			}, {
				organization:[],
				orgNo:"441826",
				name:"连南瑶族自治县"
			}, {
				organization:[],
				orgNo:"441827",
				name:"清新县"
			}, {
				organization:[],
				orgNo:"441881",
				name:"英德市"
			}, {
				organization:[],
				orgNo:"441882",
				name:"连州市"
			} ],
			orgNo:"4418",
			name:"清远市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"441900",
				name:"东莞市"
			} ],
			orgNo:"4419",
			name:"东莞市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"442000",
				name:"中山市"
			} ],
			orgNo:"4420",
			name:"中山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"445102",
				name:"湘桥区"
			}, {
				organization:[],
				orgNo:"445121",
				name:"潮安县"
			}, {
				organization:[],
				orgNo:"445122",
				name:"饶平县"
			} ],
			orgNo:"4451",
			name:"潮州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"445202",
				name:"榕城区"
			}, {
				organization:[],
				orgNo:"445221",
				name:"揭东县"
			}, {
				organization:[],
				orgNo:"445222",
				name:"揭西县"
			}, {
				organization:[],
				orgNo:"445224",
				name:"惠来县"
			}, {
				organization:[],
				orgNo:"445281",
				name:"普宁市"
			} ],
			orgNo:"4452",
			name:"揭阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"445302",
				name:"云城区"
			}, {
				organization:[],
				orgNo:"445321",
				name:"新兴县"
			}, {
				organization:[],
				orgNo:"445322",
				name:"郁南县"
			}, {
				organization:[],
				orgNo:"445323",
				name:"云安县"
			}, {
				organization:[],
				orgNo:"445381",
				name:"罗定市"
			} ],
			orgNo:"4453",
			name:"云浮市"
		} ],
		orgNo:"44",
		name:"广东"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"450102",
				name:"兴宁区"
			}, {
				organization:[],
				orgNo:"450103",
				name:"青秀区"
			}, {
				organization:[],
				orgNo:"450105",
				name:"江南区"
			}, {
				organization:[],
				orgNo:"450107",
				name:"西乡塘区"
			}, {
				organization:[],
				orgNo:"450108",
				name:"良庆区"
			}, {
				organization:[],
				orgNo:"450109",
				name:"邕宁区"
			}, {
				organization:[],
				orgNo:"450122",
				name:"武鸣县"
			}, {
				organization:[],
				orgNo:"450123",
				name:"隆安县"
			}, {
				organization:[],
				orgNo:"450124",
				name:"马山县"
			}, {
				organization:[],
				orgNo:"450125",
				name:"上林县"
			}, {
				organization:[],
				orgNo:"450126",
				name:"宾阳县"
			}, {
				organization:[],
				orgNo:"450127",
				name:"横县"
			} ],
			orgNo:"4501",
			name:"南宁市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"450202",
				name:"城中区"
			}, {
				organization:[],
				orgNo:"450203",
				name:"鱼峰区"
			}, {
				organization:[],
				orgNo:"450204",
				name:"柳南区"
			}, {
				organization:[],
				orgNo:"450205",
				name:"柳北区"
			}, {
				organization:[],
				orgNo:"450221",
				name:"柳江县"
			}, {
				organization:[],
				orgNo:"450222",
				name:"柳城县"
			}, {
				organization:[],
				orgNo:"450223",
				name:"鹿寨县"
			}, {
				organization:[],
				orgNo:"450224",
				name:"融安县"
			}, {
				organization:[],
				orgNo:"450225",
				name:"融水苗族自治县"
			}, {
				organization:[],
				orgNo:"450226",
				name:"三江侗族自治县"
			}, {
				organization:[],
				orgNo:"450227",
				name:"柳东新区"
			} ],
			orgNo:"4502",
			name:"柳州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"450302",
				name:"秀峰区"
			}, {
				organization:[],
				orgNo:"450303",
				name:"叠彩区"
			}, {
				organization:[],
				orgNo:"450304",
				name:"象山区"
			}, {
				organization:[],
				orgNo:"450305",
				name:"七星区"
			}, {
				organization:[],
				orgNo:"450311",
				name:"雁山区"
			}, {
				organization:[],
				orgNo:"450321",
				name:"阳朔县"
			}, {
				organization:[],
				orgNo:"450322",
				name:"临桂县"
			}, {
				organization:[],
				orgNo:"450323",
				name:"灵川县"
			}, {
				organization:[],
				orgNo:"450324",
				name:"全州县"
			}, {
				organization:[],
				orgNo:"450325",
				name:"兴安县"
			}, {
				organization:[],
				orgNo:"450326",
				name:"永福县"
			}, {
				organization:[],
				orgNo:"450327",
				name:"灌阳县"
			}, {
				organization:[],
				orgNo:"450328",
				name:"龙胜各族自治县"
			}, {
				organization:[],
				orgNo:"450329",
				name:"资源县"
			}, {
				organization:[],
				orgNo:"450330",
				name:"平乐县"
			}, {
				organization:[],
				orgNo:"450331",
				name:"荔浦县"
			}, {
				organization:[],
				orgNo:"450332",
				name:"恭城瑶族自治县"
			} ],
			orgNo:"4503",
			name:"桂林市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"450403",
				name:"万秀区"
			}, {
				organization:[],
				orgNo:"450404",
				name:"蝶山区"
			}, {
				organization:[],
				orgNo:"450405",
				name:"长洲区"
			}, {
				organization:[],
				orgNo:"450421",
				name:"苍梧县"
			}, {
				organization:[],
				orgNo:"450422",
				name:"藤县"
			}, {
				organization:[],
				orgNo:"450423",
				name:"蒙山县"
			}, {
				organization:[],
				orgNo:"450481",
				name:"岑溪市"
			} ],
			orgNo:"4504",
			name:"梧州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"450502",
				name:"海城区"
			}, {
				organization:[],
				orgNo:"450503",
				name:"银海区"
			}, {
				organization:[],
				orgNo:"450512",
				name:"铁山港区"
			}, {
				organization:[],
				orgNo:"450521",
				name:"合浦县"
			} ],
			orgNo:"4505",
			name:"北海市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"450602",
				name:"港口区"
			}, {
				organization:[],
				orgNo:"450603",
				name:"防城区"
			}, {
				organization:[],
				orgNo:"450621",
				name:"上思县"
			}, {
				organization:[],
				orgNo:"450681",
				name:"东兴市"
			} ],
			orgNo:"4506",
			name:"防城港市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"450702",
				name:"钦南区"
			}, {
				organization:[],
				orgNo:"450703",
				name:"钦北区"
			}, {
				organization:[],
				orgNo:"450721",
				name:"灵山县"
			}, {
				organization:[],
				orgNo:"450722",
				name:"浦北县"
			} ],
			orgNo:"4507",
			name:"钦州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"450802",
				name:"港北区"
			}, {
				organization:[],
				orgNo:"450803",
				name:"港南区"
			}, {
				organization:[],
				orgNo:"450804",
				name:"覃塘区"
			}, {
				organization:[],
				orgNo:"450821",
				name:"平南县"
			}, {
				organization:[],
				orgNo:"450881",
				name:"桂平市"
			} ],
			orgNo:"4508",
			name:"贵港市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"450902",
				name:"玉州区"
			}, {
				organization:[],
				orgNo:"450921",
				name:"容县"
			}, {
				organization:[],
				orgNo:"450922",
				name:"陆川县"
			}, {
				organization:[],
				orgNo:"450923",
				name:"博白县"
			}, {
				organization:[],
				orgNo:"450924",
				name:"兴业县"
			}, {
				organization:[],
				orgNo:"450981",
				name:"北流市"
			} ],
			orgNo:"4509",
			name:"玉林市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"451002",
				name:"右江区"
			}, {
				organization:[],
				orgNo:"451021",
				name:"田阳县"
			}, {
				organization:[],
				orgNo:"451022",
				name:"田东县"
			}, {
				organization:[],
				orgNo:"451023",
				name:"平果县"
			}, {
				organization:[],
				orgNo:"451024",
				name:"德保县"
			}, {
				organization:[],
				orgNo:"451025",
				name:"靖西县"
			}, {
				organization:[],
				orgNo:"451026",
				name:"那坡县"
			}, {
				organization:[],
				orgNo:"451027",
				name:"凌云县"
			}, {
				organization:[],
				orgNo:"451028",
				name:"乐业县"
			}, {
				organization:[],
				orgNo:"451029",
				name:"田林县"
			}, {
				organization:[],
				orgNo:"451030",
				name:"西林县"
			}, {
				organization:[],
				orgNo:"451031",
				name:"隆林各族自治县"
			} ],
			orgNo:"4510",
			name:"百色市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"451102",
				name:"八步区"
			}, {
				organization:[],
				orgNo:"451121",
				name:"昭平县"
			}, {
				organization:[],
				orgNo:"451122",
				name:"钟山县"
			}, {
				organization:[],
				orgNo:"451123",
				name:"富川瑶族自治县"
			} ],
			orgNo:"4511",
			name:"贺州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"451202",
				name:"金城江区"
			}, {
				organization:[],
				orgNo:"451221",
				name:"南丹县"
			}, {
				organization:[],
				orgNo:"451222",
				name:"天峨县"
			}, {
				organization:[],
				orgNo:"451223",
				name:"凤山县"
			}, {
				organization:[],
				orgNo:"451224",
				name:"东兰县"
			}, {
				organization:[],
				orgNo:"451225",
				name:"罗城仫佬族自治县"
			}, {
				organization:[],
				orgNo:"451226",
				name:"环江毛南族自治县"
			}, {
				organization:[],
				orgNo:"451227",
				name:"巴马瑶族自治县"
			}, {
				organization:[],
				orgNo:"451228",
				name:"都安瑶族自治县"
			}, {
				organization:[],
				orgNo:"451229",
				name:"大化瑶族自治县"
			}, {
				organization:[],
				orgNo:"451281",
				name:"宜州市"
			} ],
			orgNo:"4512",
			name:"河池市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"451302",
				name:"兴宾区"
			}, {
				organization:[],
				orgNo:"451321",
				name:"忻城县"
			}, {
				organization:[],
				orgNo:"451322",
				name:"象州县"
			}, {
				organization:[],
				orgNo:"451323",
				name:"武宣县"
			}, {
				organization:[],
				orgNo:"451324",
				name:"金秀瑶族自治县"
			}, {
				organization:[],
				orgNo:"451381",
				name:"合山市"
			} ],
			orgNo:"4513",
			name:"来宾市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"451402",
				name:"江州区"
			}, {
				organization:[],
				orgNo:"451421",
				name:"扶绥县"
			}, {
				organization:[],
				orgNo:"451422",
				name:"宁明县"
			}, {
				organization:[],
				orgNo:"451423",
				name:"龙州县"
			}, {
				organization:[],
				orgNo:"451424",
				name:"大新县"
			}, {
				organization:[],
				orgNo:"451425",
				name:"天等县"
			}, {
				organization:[],
				orgNo:"451481",
				name:"凭祥市"
			} ],
			orgNo:"4514",
			name:"崇左市"
		} ],
		orgNo:"45",
		name:"广西"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"460105",
				name:"秀英区"
			}, {
				organization:[],
				orgNo:"460106",
				name:"龙华区"
			}, {
				organization:[],
				orgNo:"460107",
				name:"琼山区"
			}, {
				organization:[],
				orgNo:"460108",
				name:"美兰区"
			} ],
			orgNo:"4601",
			name:"海口市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"460200",
				name:"三亚市"
			} ],
			orgNo:"4602",
			name:"三亚市"
		}, {
			organization:[],
			orgNo:"469001",
			name:"五指山市"
		}, {
			organization:[],
			orgNo:"469002",
			name:"琼海市"
		}, {
			organization:[],
			orgNo:"469003",
			name:"儋州市"
		}, {
			organization:[],
			orgNo:"469005",
			name:"文昌市"
		}, {
			organization:[],
			orgNo:"469006",
			name:"万宁市"
		}, {
			organization:[],
			orgNo:"469007",
			name:"东方市"
		}, {
			organization:[],
			orgNo:"469025",
			name:"定安县"
		}, {
			organization:[],
			orgNo:"469026",
			name:"屯昌县"
		}, {
			organization:[],
			orgNo:"469027",
			name:"澄迈县"
		}, {
			organization:[],
			orgNo:"469028",
			name:"临高县"
		}, {
			organization:[],
			orgNo:"469030",
			name:"白沙黎族自治县"
		}, {
			organization:[],
			orgNo:"469031",
			name:"昌江黎族自治县"
		}, {
			organization:[],
			orgNo:"469033",
			name:"乐东黎族自治县"
		}, {
			organization:[],
			orgNo:"469034",
			name:"陵水黎族自治县"
		}, {
			organization:[],
			orgNo:"469035",
			name:"保亭黎族苗族自治县"
		}, {
			organization:[],
			orgNo:"469036",
			name:"琼中黎族苗族自治县"
		} ],
		orgNo:"46",
		name:"海南"
	}, {
		organization:[ {
			organization:[],
			orgNo:"500101",
			name:"万州区"
		}, {
			organization:[],
			orgNo:"500102",
			name:"涪陵区"
		}, {
			organization:[],
			orgNo:"500103",
			name:"渝中区"
		}, {
			organization:[],
			orgNo:"500104",
			name:"大渡口区"
		}, {
			organization:[],
			orgNo:"500105",
			name:"江北区"
		}, {
			organization:[],
			orgNo:"500106",
			name:"沙坪坝区"
		}, {
			organization:[],
			orgNo:"500107",
			name:"九龙坡区"
		}, {
			organization:[],
			orgNo:"500108",
			name:"南岸区"
		}, {
			organization:[],
			orgNo:"500109",
			name:"北碚区"
		}, {
			organization:[],
			orgNo:"500110",
			name:"万盛区"
		}, {
			organization:[],
			orgNo:"500111",
			name:"双桥区"
		}, {
			organization:[],
			orgNo:"500112",
			name:"渝北区"
		}, {
			organization:[],
			orgNo:"500113",
			name:"巴南区"
		}, {
			organization:[],
			orgNo:"500114",
			name:"黔江区"
		}, {
			organization:[],
			orgNo:"500115",
			name:"长寿区"
		}, {
			organization:[],
			orgNo:"500116",
			name:"江津区"
		}, {
			organization:[],
			orgNo:"500117",
			name:"合川区"
		}, {
			organization:[],
			orgNo:"500118",
			name:"永川区"
		}, {
			organization:[],
			orgNo:"500119",
			name:"南川区"
		}, {
			organization:[],
			orgNo:"500222",
			name:"綦江县"
		}, {
			organization:[],
			orgNo:"500223",
			name:"潼南县"
		}, {
			organization:[],
			orgNo:"500224",
			name:"铜梁县"
		}, {
			organization:[],
			orgNo:"500225",
			name:"大足县"
		}, {
			organization:[],
			orgNo:"500226",
			name:"荣昌县"
		}, {
			organization:[],
			orgNo:"500227",
			name:"璧山县"
		}, {
			organization:[],
			orgNo:"500228",
			name:"梁平县"
		}, {
			organization:[],
			orgNo:"500229",
			name:"城口县"
		}, {
			organization:[],
			orgNo:"500230",
			name:"丰都县"
		}, {
			organization:[],
			orgNo:"500231",
			name:"垫江县"
		}, {
			organization:[],
			orgNo:"500232",
			name:"武隆县"
		}, {
			organization:[],
			orgNo:"500233",
			name:"忠县"
		}, {
			organization:[],
			orgNo:"500234",
			name:"开县"
		}, {
			organization:[],
			orgNo:"500235",
			name:"云阳县"
		}, {
			organization:[],
			orgNo:"500236",
			name:"奉节县"
		}, {
			organization:[],
			orgNo:"500237",
			name:"巫山县"
		}, {
			organization:[],
			orgNo:"500238",
			name:"巫溪县"
		}, {
			organization:[],
			orgNo:"500240",
			name:"石柱土家族自治县"
		}, {
			organization:[],
			orgNo:"500241",
			name:"秀山土家族苗族自治县"
		}, {
			organization:[],
			orgNo:"500242",
			name:"酉阳土家族苗族自治县"
		}, {
			organization:[],
			orgNo:"500243",
			name:"彭水苗族土家族自治县"
		} ],
		orgNo:"50",
		name:"重庆"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"510104",
				name:"锦江区"
			}, {
				organization:[],
				orgNo:"510105",
				name:"青羊区"
			}, {
				organization:[],
				orgNo:"510106",
				name:"金牛区"
			}, {
				organization:[],
				orgNo:"510107",
				name:"武侯区"
			}, {
				organization:[],
				orgNo:"510108",
				name:"成华区"
			}, {
				organization:[],
				orgNo:"510112",
				name:"龙泉驿区"
			}, {
				organization:[],
				orgNo:"510113",
				name:"青白江区"
			}, {
				organization:[],
				orgNo:"510114",
				name:"新都区"
			}, {
				organization:[],
				orgNo:"510115",
				name:"温江区"
			}, {
				organization:[],
				orgNo:"510121",
				name:"金堂县"
			}, {
				organization:[],
				orgNo:"510122",
				name:"双流县"
			}, {
				organization:[],
				orgNo:"510124",
				name:"郫县"
			}, {
				organization:[],
				orgNo:"510129",
				name:"大邑县"
			}, {
				organization:[],
				orgNo:"510131",
				name:"蒲江县"
			}, {
				organization:[],
				orgNo:"510132",
				name:"新津县"
			}, {
				organization:[],
				orgNo:"510181",
				name:"都江堰市"
			}, {
				organization:[],
				orgNo:"510182",
				name:"彭州市"
			}, {
				organization:[],
				orgNo:"510183",
				name:"邛崃市"
			}, {
				organization:[],
				orgNo:"510184",
				name:"崇州市"
			} ],
			orgNo:"5101",
			name:"成都市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"510302",
				name:"自流井区"
			}, {
				organization:[],
				orgNo:"510303",
				name:"贡井区"
			}, {
				organization:[],
				orgNo:"510304",
				name:"大安区"
			}, {
				organization:[],
				orgNo:"510311",
				name:"盐滩区"
			}, {
				organization:[],
				orgNo:"510321",
				name:"荣县"
			}, {
				organization:[],
				orgNo:"510322",
				name:"富顺县"
			} ],
			orgNo:"5103",
			name:"自贡市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"510402",
				name:"东区"
			}, {
				organization:[],
				orgNo:"510403",
				name:"西区"
			}, {
				organization:[],
				orgNo:"510411",
				name:"仁和区"
			}, {
				organization:[],
				orgNo:"510421",
				name:"米易县"
			}, {
				organization:[],
				orgNo:"510422",
				name:"盐边县"
			} ],
			orgNo:"5104",
			name:"攀枝花市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"510502",
				name:"江阳区"
			}, {
				organization:[],
				orgNo:"510503",
				name:"纳溪区"
			}, {
				organization:[],
				orgNo:"510504",
				name:"龙马潭区"
			}, {
				organization:[],
				orgNo:"510521",
				name:"泸县"
			}, {
				organization:[],
				orgNo:"510522",
				name:"合江县"
			}, {
				organization:[],
				orgNo:"510524",
				name:"叙永县"
			}, {
				organization:[],
				orgNo:"510525",
				name:"古蔺县"
			} ],
			orgNo:"5105",
			name:"泸州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"510603",
				name:"旌阳区"
			}, {
				organization:[],
				orgNo:"510623",
				name:"中江县"
			}, {
				organization:[],
				orgNo:"510626",
				name:"罗江县"
			}, {
				organization:[],
				orgNo:"510681",
				name:"广汉市"
			}, {
				organization:[],
				orgNo:"510682",
				name:"什邡市"
			}, {
				organization:[],
				orgNo:"510683",
				name:"绵竹市"
			} ],
			orgNo:"5106",
			name:"德阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"510703",
				name:"涪城区"
			}, {
				organization:[],
				orgNo:"510704",
				name:"游仙区"
			}, {
				organization:[],
				orgNo:"510722",
				name:"三台县"
			}, {
				organization:[],
				orgNo:"510723",
				name:"盐亭县"
			}, {
				organization:[],
				orgNo:"510724",
				name:"安县"
			}, {
				organization:[],
				orgNo:"510725",
				name:"梓潼县"
			}, {
				organization:[],
				orgNo:"510726",
				name:"北川羌族自治县"
			}, {
				organization:[],
				orgNo:"510727",
				name:"平武县"
			}, {
				organization:[],
				orgNo:"510781",
				name:"江油市"
			} ],
			orgNo:"5107",
			name:"绵阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"510802",
				name:"利州区"
			}, {
				organization:[],
				orgNo:"510811",
				name:"元坝区"
			}, {
				organization:[],
				orgNo:"510812",
				name:"朝天区"
			}, {
				organization:[],
				orgNo:"510821",
				name:"旺苍县"
			}, {
				organization:[],
				orgNo:"510822",
				name:"青川县"
			}, {
				organization:[],
				orgNo:"510823",
				name:"剑阁县"
			}, {
				organization:[],
				orgNo:"510824",
				name:"苍溪县"
			} ],
			orgNo:"5108",
			name:"广元市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"510903",
				name:"船山区"
			}, {
				organization:[],
				orgNo:"510904",
				name:"安居区"
			}, {
				organization:[],
				orgNo:"510921",
				name:"蓬溪县"
			}, {
				organization:[],
				orgNo:"510922",
				name:"射洪县"
			}, {
				organization:[],
				orgNo:"510923",
				name:"大英县"
			} ],
			orgNo:"5109",
			name:"遂宁市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"511002",
				name:"市中区"
			}, {
				organization:[],
				orgNo:"511011",
				name:"东兴区"
			}, {
				organization:[],
				orgNo:"511024",
				name:"威远县"
			}, {
				organization:[],
				orgNo:"511025",
				name:"资中县"
			}, {
				organization:[],
				orgNo:"511028",
				name:"隆昌县"
			} ],
			orgNo:"5110",
			name:"内江市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"511102",
				name:"市中区"
			}, {
				organization:[],
				orgNo:"511111",
				name:"沙湾区"
			}, {
				organization:[],
				orgNo:"511112",
				name:"五通桥区"
			}, {
				organization:[],
				orgNo:"511113",
				name:"金口河区"
			}, {
				organization:[],
				orgNo:"511123",
				name:"犍为县"
			}, {
				organization:[],
				orgNo:"511124",
				name:"井研县"
			}, {
				organization:[],
				orgNo:"511126",
				name:"夹江县"
			}, {
				organization:[],
				orgNo:"511129",
				name:"沐川县"
			}, {
				organization:[],
				orgNo:"511132",
				name:"峨边彝族自治县"
			}, {
				organization:[],
				orgNo:"511133",
				name:"马边彝族自治县"
			}, {
				organization:[],
				orgNo:"511181",
				name:"峨眉山市"
			} ],
			orgNo:"5111",
			name:"乐山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"511302",
				name:"顺庆区"
			}, {
				organization:[],
				orgNo:"511303",
				name:"高坪区"
			}, {
				organization:[],
				orgNo:"511304",
				name:"嘉陵区"
			}, {
				organization:[],
				orgNo:"511321",
				name:"南部县"
			}, {
				organization:[],
				orgNo:"511322",
				name:"营山县"
			}, {
				organization:[],
				orgNo:"511323",
				name:"蓬安县"
			}, {
				organization:[],
				orgNo:"511324",
				name:"仪陇县"
			}, {
				organization:[],
				orgNo:"511325",
				name:"西充县"
			}, {
				organization:[],
				orgNo:"511381",
				name:"阆中市"
			} ],
			orgNo:"5113",
			name:"南充市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"511402",
				name:"东坡区"
			}, {
				organization:[],
				orgNo:"511421",
				name:"仁寿县"
			}, {
				organization:[],
				orgNo:"511422",
				name:"彭山县"
			}, {
				organization:[],
				orgNo:"511423",
				name:"洪雅县"
			}, {
				organization:[],
				orgNo:"511424",
				name:"丹棱县"
			}, {
				organization:[],
				orgNo:"511425",
				name:"青神县"
			} ],
			orgNo:"5114",
			name:"眉山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"511502",
				name:"翠屏区"
			}, {
				organization:[],
				orgNo:"511521",
				name:"宜宾县"
			}, {
				organization:[],
				orgNo:"511522",
				name:"南溪县"
			}, {
				organization:[],
				orgNo:"511523",
				name:"江安县"
			}, {
				organization:[],
				orgNo:"511524",
				name:"长宁县"
			}, {
				organization:[],
				orgNo:"511525",
				name:"高县"
			}, {
				organization:[],
				orgNo:"511526",
				name:"珙县"
			}, {
				organization:[],
				orgNo:"511527",
				name:"筠连县"
			}, {
				organization:[],
				orgNo:"511528",
				name:"兴文县"
			}, {
				organization:[],
				orgNo:"511529",
				name:"屏山县"
			} ],
			orgNo:"5115",
			name:"宜宾市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"511602",
				name:"广安区"
			}, {
				organization:[],
				orgNo:"511621",
				name:"岳池县"
			}, {
				organization:[],
				orgNo:"511622",
				name:"武胜县"
			}, {
				organization:[],
				orgNo:"511623",
				name:"邻水县"
			}, {
				organization:[],
				orgNo:"511681",
				name:"华蓥市"
			} ],
			orgNo:"5116",
			name:"广安市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"511702",
				name:"通川区"
			}, {
				organization:[],
				orgNo:"511721",
				name:"达县"
			}, {
				organization:[],
				orgNo:"511722",
				name:"宣汉县"
			}, {
				organization:[],
				orgNo:"511723",
				name:"开江县"
			}, {
				organization:[],
				orgNo:"511724",
				name:"大竹县"
			}, {
				organization:[],
				orgNo:"511725",
				name:"渠县"
			}, {
				organization:[],
				orgNo:"511781",
				name:"万源市"
			} ],
			orgNo:"5117",
			name:"达州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"511802",
				name:"雨城区"
			}, {
				organization:[],
				orgNo:"511821",
				name:"名山县"
			}, {
				organization:[],
				orgNo:"511822",
				name:"荥经县"
			}, {
				organization:[],
				orgNo:"511823",
				name:"汉源县"
			}, {
				organization:[],
				orgNo:"511824",
				name:"石棉县"
			}, {
				organization:[],
				orgNo:"511825",
				name:"天全县"
			}, {
				organization:[],
				orgNo:"511826",
				name:"芦山县"
			}, {
				organization:[],
				orgNo:"511827",
				name:"宝兴县"
			} ],
			orgNo:"5118",
			name:"雅安市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"511902",
				name:"巴州区"
			}, {
				organization:[],
				orgNo:"511921",
				name:"通江县"
			}, {
				organization:[],
				orgNo:"511922",
				name:"南江县"
			}, {
				organization:[],
				orgNo:"511923",
				name:"平昌县"
			} ],
			orgNo:"5119",
			name:"巴中市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"512002",
				name:"雁江区"
			}, {
				organization:[],
				orgNo:"512021",
				name:"安岳县"
			}, {
				organization:[],
				orgNo:"512022",
				name:"乐至县"
			}, {
				organization:[],
				orgNo:"512081",
				name:"简阳市"
			} ],
			orgNo:"5120",
			name:"资阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"513221",
				name:"汶川县"
			}, {
				organization:[],
				orgNo:"513222",
				name:"理县"
			}, {
				organization:[],
				orgNo:"513223",
				name:"茂县"
			}, {
				organization:[],
				orgNo:"513224",
				name:"松潘县"
			}, {
				organization:[],
				orgNo:"513225",
				name:"九寨沟县"
			}, {
				organization:[],
				orgNo:"513226",
				name:"金川县"
			}, {
				organization:[],
				orgNo:"513227",
				name:"小金县"
			}, {
				organization:[],
				orgNo:"513228",
				name:"黑水县"
			}, {
				organization:[],
				orgNo:"513229",
				name:"马尔康县"
			}, {
				organization:[],
				orgNo:"513230",
				name:"壤塘县"
			}, {
				organization:[],
				orgNo:"513231",
				name:"阿坝县"
			}, {
				organization:[],
				orgNo:"513232",
				name:"若尔盖县"
			}, {
				organization:[],
				orgNo:"513233",
				name:"红原县"
			} ],
			orgNo:"5132",
			name:"阿坝藏族羌族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"513321",
				name:"康定县"
			}, {
				organization:[],
				orgNo:"513322",
				name:"泸定县"
			}, {
				organization:[],
				orgNo:"513323",
				name:"丹巴县"
			}, {
				organization:[],
				orgNo:"513324",
				name:"九龙县"
			}, {
				organization:[],
				orgNo:"513325",
				name:"雅江县"
			}, {
				organization:[],
				orgNo:"513326",
				name:"道孚县"
			}, {
				organization:[],
				orgNo:"513327",
				name:"炉霍县"
			}, {
				organization:[],
				orgNo:"513328",
				name:"甘孜县"
			}, {
				organization:[],
				orgNo:"513329",
				name:"新龙县"
			}, {
				organization:[],
				orgNo:"513330",
				name:"德格县"
			}, {
				organization:[],
				orgNo:"513331",
				name:"白玉县"
			}, {
				organization:[],
				orgNo:"513332",
				name:"石渠县"
			}, {
				organization:[],
				orgNo:"513333",
				name:"色达县"
			}, {
				organization:[],
				orgNo:"513334",
				name:"理塘县"
			}, {
				organization:[],
				orgNo:"513335",
				name:"巴塘县"
			}, {
				organization:[],
				orgNo:"513336",
				name:"乡城县"
			}, {
				organization:[],
				orgNo:"513337",
				name:"稻城县"
			}, {
				organization:[],
				orgNo:"513338",
				name:"得荣县"
			} ],
			orgNo:"5133",
			name:"甘孜藏族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"513401",
				name:"西昌市"
			}, {
				organization:[],
				orgNo:"513422",
				name:"木里藏族自治县"
			}, {
				organization:[],
				orgNo:"513423",
				name:"盐源县"
			}, {
				organization:[],
				orgNo:"513424",
				name:"德昌县"
			}, {
				organization:[],
				orgNo:"513425",
				name:"会理县"
			}, {
				organization:[],
				orgNo:"513426",
				name:"会东县"
			}, {
				organization:[],
				orgNo:"513427",
				name:"宁南县"
			}, {
				organization:[],
				orgNo:"513428",
				name:"普格县"
			}, {
				organization:[],
				orgNo:"513429",
				name:"布拖县"
			}, {
				organization:[],
				orgNo:"513430",
				name:"金阳县"
			}, {
				organization:[],
				orgNo:"513431",
				name:"昭觉县"
			}, {
				organization:[],
				orgNo:"513432",
				name:"喜德县"
			}, {
				organization:[],
				orgNo:"513433",
				name:"冕宁县"
			}, {
				organization:[],
				orgNo:"513434",
				name:"越西县"
			}, {
				organization:[],
				orgNo:"513435",
				name:"甘洛县"
			}, {
				organization:[],
				orgNo:"513436",
				name:"美姑县"
			}, {
				organization:[],
				orgNo:"513437",
				name:"雷波县"
			} ],
			orgNo:"5134",
			name:"凉山彝族自治州"
		} ],
		orgNo:"51",
		name:"四川"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"520102",
				name:"南明区"
			}, {
				organization:[],
				orgNo:"520103",
				name:"云岩区"
			}, {
				organization:[],
				orgNo:"520111",
				name:"花溪区"
			}, {
				organization:[],
				orgNo:"520112",
				name:"乌当区"
			}, {
				organization:[],
				orgNo:"520113",
				name:"白云区"
			}, {
				organization:[],
				orgNo:"520114",
				name:"小河区"
			}, {
				organization:[],
				orgNo:"520121",
				name:"开阳县"
			}, {
				organization:[],
				orgNo:"520122",
				name:"息烽县"
			}, {
				organization:[],
				orgNo:"520123",
				name:"修文县"
			}, {
				organization:[],
				orgNo:"520181",
				name:"清镇市"
			} ],
			orgNo:"5201",
			name:"贵阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"520201",
				name:"钟山区"
			}, {
				organization:[],
				orgNo:"520203",
				name:"六枝特区"
			}, {
				organization:[],
				orgNo:"520221",
				name:"水城县"
			}, {
				organization:[],
				orgNo:"520222",
				name:"盘县"
			} ],
			orgNo:"5202",
			name:"六盘水市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"520302",
				name:"红花岗区"
			}, {
				organization:[],
				orgNo:"520303",
				name:"汇川区"
			}, {
				organization:[],
				orgNo:"520321",
				name:"遵义县"
			}, {
				organization:[],
				orgNo:"520322",
				name:"桐梓县"
			}, {
				organization:[],
				orgNo:"520323",
				name:"绥阳县"
			}, {
				organization:[],
				orgNo:"520324",
				name:"正安县"
			}, {
				organization:[],
				orgNo:"520325",
				name:"道真仡佬族苗族自治县"
			}, {
				organization:[],
				orgNo:"520326",
				name:"务川仡佬族苗族自治县"
			}, {
				organization:[],
				orgNo:"520327",
				name:"凤冈县"
			}, {
				organization:[],
				orgNo:"520328",
				name:"湄潭县"
			}, {
				organization:[],
				orgNo:"520329",
				name:"余庆县"
			}, {
				organization:[],
				orgNo:"520330",
				name:"习水县"
			}, {
				organization:[],
				orgNo:"520381",
				name:"赤水市"
			}, {
				organization:[],
				orgNo:"520382",
				name:"仁怀市"
			} ],
			orgNo:"5203",
			name:"遵义市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"520402",
				name:"西秀区"
			}, {
				organization:[],
				orgNo:"520421",
				name:"平坝县"
			}, {
				organization:[],
				orgNo:"520422",
				name:"普定县"
			}, {
				organization:[],
				orgNo:"520423",
				name:"镇宁布依族苗族自治县"
			}, {
				organization:[],
				orgNo:"520424",
				name:"关岭布依族苗族自治县"
			}, {
				organization:[],
				orgNo:"520425",
				name:"紫云苗族布依族自治县"
			} ],
			orgNo:"5204",
			name:"安顺市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"522201",
				name:"铜仁市"
			}, {
				organization:[],
				orgNo:"522222",
				name:"江口县"
			}, {
				organization:[],
				orgNo:"522223",
				name:"玉屏侗族自治县"
			}, {
				organization:[],
				orgNo:"522224",
				name:"石阡县"
			}, {
				organization:[],
				orgNo:"522225",
				name:"思南县"
			}, {
				organization:[],
				orgNo:"522226",
				name:"印江土家族苗族自治县"
			}, {
				organization:[],
				orgNo:"522227",
				name:"德江县"
			}, {
				organization:[],
				orgNo:"522228",
				name:"沿河土家族自治县"
			}, {
				organization:[],
				orgNo:"522229",
				name:"松桃苗族自治县"
			}, {
				organization:[],
				orgNo:"522230",
				name:"万山特区"
			} ],
			orgNo:"5222",
			name:"铜仁地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"522301",
				name:"兴义市"
			}, {
				organization:[],
				orgNo:"522322",
				name:"兴仁县"
			}, {
				organization:[],
				orgNo:"522323",
				name:"普安县"
			}, {
				organization:[],
				orgNo:"522324",
				name:"晴隆县"
			}, {
				organization:[],
				orgNo:"522325",
				name:"贞丰县"
			}, {
				organization:[],
				orgNo:"522326",
				name:"望谟县"
			}, {
				organization:[],
				orgNo:"522327",
				name:"册亨县"
			}, {
				organization:[],
				orgNo:"522328",
				name:"安龙县"
			} ],
			orgNo:"5223",
			name:"黔西南布依族苗族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"522401",
				name:"毕节市"
			}, {
				organization:[],
				orgNo:"522422",
				name:"大方县"
			}, {
				organization:[],
				orgNo:"522423",
				name:"黔西县"
			}, {
				organization:[],
				orgNo:"522424",
				name:"金沙县"
			}, {
				organization:[],
				orgNo:"522425",
				name:"织金县"
			}, {
				organization:[],
				orgNo:"522426",
				name:"纳雍县"
			}, {
				organization:[],
				orgNo:"522427",
				name:"威宁彝族回族苗族自治县"
			}, {
				organization:[],
				orgNo:"522428",
				name:"赫章县"
			} ],
			orgNo:"5224",
			name:"毕节地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"522601",
				name:"凯里市"
			}, {
				organization:[],
				orgNo:"522622",
				name:"黄平县"
			}, {
				organization:[],
				orgNo:"522623",
				name:"施秉县"
			}, {
				organization:[],
				orgNo:"522624",
				name:"三穗县"
			}, {
				organization:[],
				orgNo:"522625",
				name:"镇远县"
			}, {
				organization:[],
				orgNo:"522626",
				name:"岑巩县"
			}, {
				organization:[],
				orgNo:"522627",
				name:"天柱县"
			}, {
				organization:[],
				orgNo:"522628",
				name:"锦屏县"
			}, {
				organization:[],
				orgNo:"522629",
				name:"剑河县"
			}, {
				organization:[],
				orgNo:"522630",
				name:"台江县"
			}, {
				organization:[],
				orgNo:"522631",
				name:"黎平县"
			}, {
				organization:[],
				orgNo:"522632",
				name:"榕江县"
			}, {
				organization:[],
				orgNo:"522633",
				name:"从江县"
			}, {
				organization:[],
				orgNo:"522634",
				name:"雷山县"
			}, {
				organization:[],
				orgNo:"522635",
				name:"麻江县"
			}, {
				organization:[],
				orgNo:"522636",
				name:"丹寨县"
			} ],
			orgNo:"5226",
			name:"黔东南苗族侗族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"522701",
				name:"都匀市"
			}, {
				organization:[],
				orgNo:"522702",
				name:"福泉市"
			}, {
				organization:[],
				orgNo:"522722",
				name:"荔波县"
			}, {
				organization:[],
				orgNo:"522723",
				name:"贵定县"
			}, {
				organization:[],
				orgNo:"522725",
				name:"瓮安县"
			}, {
				organization:[],
				orgNo:"522726",
				name:"独山县"
			}, {
				organization:[],
				orgNo:"522727",
				name:"平塘县"
			}, {
				organization:[],
				orgNo:"522728",
				name:"罗甸县"
			}, {
				organization:[],
				orgNo:"522729",
				name:"长顺县"
			}, {
				organization:[],
				orgNo:"522730",
				name:"龙里县"
			}, {
				organization:[],
				orgNo:"522731",
				name:"惠水县"
			}, {
				organization:[],
				orgNo:"522732",
				name:"三都水族自治县"
			} ],
			orgNo:"5227",
			name:"黔南布依族苗族自治州"
		} ],
		orgNo:"52",
		name:"贵州"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"530102",
				name:"五华区"
			}, {
				organization:[],
				orgNo:"530103",
				name:"盘龙区"
			}, {
				organization:[],
				orgNo:"530111",
				name:"官渡区"
			}, {
				organization:[],
				orgNo:"530112",
				name:"西山区"
			}, {
				organization:[],
				orgNo:"530113",
				name:"东川区"
			}, {
				organization:[],
				orgNo:"530121",
				name:"呈贡县"
			}, {
				organization:[],
				orgNo:"530122",
				name:"晋宁县"
			}, {
				organization:[],
				orgNo:"530124",
				name:"富民县"
			}, {
				organization:[],
				orgNo:"530125",
				name:"宜良县"
			}, {
				organization:[],
				orgNo:"530126",
				name:"石林彝族自治县"
			}, {
				organization:[],
				orgNo:"530127",
				name:"嵩明县"
			}, {
				organization:[],
				orgNo:"530128",
				name:"禄劝彝族苗族自治县"
			}, {
				organization:[],
				orgNo:"530129",
				name:"寻甸回族彝族自治县"
			}, {
				organization:[],
				orgNo:"530181",
				name:"安宁市"
			} ],
			orgNo:"5301",
			name:"昆明市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"530302",
				name:"麒麟区"
			}, {
				organization:[],
				orgNo:"530321",
				name:"马龙县"
			}, {
				organization:[],
				orgNo:"530322",
				name:"陆良县"
			}, {
				organization:[],
				orgNo:"530323",
				name:"师宗县"
			}, {
				organization:[],
				orgNo:"530324",
				name:"罗平县"
			}, {
				organization:[],
				orgNo:"530325",
				name:"富源县"
			}, {
				organization:[],
				orgNo:"530326",
				name:"会泽县"
			}, {
				organization:[],
				orgNo:"530328",
				name:"沾益县"
			}, {
				organization:[],
				orgNo:"530381",
				name:"宣威市"
			} ],
			orgNo:"5303",
			name:"曲靖市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"530402",
				name:"红塔区"
			}, {
				organization:[],
				orgNo:"530421",
				name:"江川县"
			}, {
				organization:[],
				orgNo:"530422",
				name:"澄江县"
			}, {
				organization:[],
				orgNo:"530423",
				name:"通海县"
			}, {
				organization:[],
				orgNo:"530424",
				name:"华宁县"
			}, {
				organization:[],
				orgNo:"530425",
				name:"易门县"
			}, {
				organization:[],
				orgNo:"530426",
				name:"峨山彝族自治县"
			}, {
				organization:[],
				orgNo:"530427",
				name:"新平彝族傣族自治县"
			}, {
				organization:[],
				orgNo:"530428",
				name:"元江哈尼族彝族傣族自治县"
			} ],
			orgNo:"5304",
			name:"玉溪市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"530502",
				name:"隆阳区"
			}, {
				organization:[],
				orgNo:"530521",
				name:"施甸县"
			}, {
				organization:[],
				orgNo:"530522",
				name:"腾冲县"
			}, {
				organization:[],
				orgNo:"530523",
				name:"龙陵县"
			}, {
				organization:[],
				orgNo:"530524",
				name:"昌宁县"
			} ],
			orgNo:"5305",
			name:"保山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"530602",
				name:"昭阳区"
			}, {
				organization:[],
				orgNo:"530621",
				name:"鲁甸县"
			}, {
				organization:[],
				orgNo:"530622",
				name:"巧家县"
			}, {
				organization:[],
				orgNo:"530623",
				name:"盐津县"
			}, {
				organization:[],
				orgNo:"530624",
				name:"大关县"
			}, {
				organization:[],
				orgNo:"530625",
				name:"永善县"
			}, {
				organization:[],
				orgNo:"530626",
				name:"绥江县"
			}, {
				organization:[],
				orgNo:"530627",
				name:"镇雄县"
			}, {
				organization:[],
				orgNo:"530628",
				name:"彝良县"
			}, {
				organization:[],
				orgNo:"530629",
				name:"威信县"
			}, {
				organization:[],
				orgNo:"530630",
				name:"水富县"
			} ],
			orgNo:"5306",
			name:"昭通市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"530702",
				name:"古城区"
			}, {
				organization:[],
				orgNo:"530721",
				name:"玉龙纳西族自治县"
			}, {
				organization:[],
				orgNo:"530722",
				name:"永胜县"
			}, {
				organization:[],
				orgNo:"530723",
				name:"华坪县"
			}, {
				organization:[],
				orgNo:"530724",
				name:"宁蒗彝族自治县"
			} ],
			orgNo:"5307",
			name:"丽江市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"530802",
				name:"思茅区"
			}, {
				organization:[],
				orgNo:"530821",
				name:"普洱哈尼族彝族自治县"
			}, {
				organization:[],
				orgNo:"530822",
				name:"墨江哈尼族自治县"
			}, {
				organization:[],
				orgNo:"530823",
				name:"景东彝族自治县"
			}, {
				organization:[],
				orgNo:"530824",
				name:"景谷傣族彝族自治县"
			}, {
				organization:[],
				orgNo:"530825",
				name:"镇沅彝族哈尼族拉祜族自治县"
			}, {
				organization:[],
				orgNo:"530826",
				name:"江城哈尼族彝族自治县"
			}, {
				organization:[],
				orgNo:"530827",
				name:"孟连傣族拉祜族佤族自治县"
			}, {
				organization:[],
				orgNo:"530828",
				name:"澜沧拉祜族自治县"
			}, {
				organization:[],
				orgNo:"530829",
				name:"西盟佤族自治县"
			} ],
			orgNo:"5308",
			name:"普洱市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"530902",
				name:"临翔区"
			}, {
				organization:[],
				orgNo:"530921",
				name:"凤庆县"
			}, {
				organization:[],
				orgNo:"530922",
				name:"云县"
			}, {
				organization:[],
				orgNo:"530923",
				name:"永德县"
			}, {
				organization:[],
				orgNo:"530924",
				name:"镇康县"
			}, {
				organization:[],
				orgNo:"530925",
				name:"双江拉祜族佤族布朗族傣族自治县"
			}, {
				organization:[],
				orgNo:"530926",
				name:"耿马傣族佤族自治县"
			}, {
				organization:[],
				orgNo:"530927",
				name:"沧源佤族自治县"
			} ],
			orgNo:"5309",
			name:"临沧市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"532301",
				name:"楚雄市"
			}, {
				organization:[],
				orgNo:"532322",
				name:"双柏县"
			}, {
				organization:[],
				orgNo:"532323",
				name:"牟定县"
			}, {
				organization:[],
				orgNo:"532324",
				name:"南华县"
			}, {
				organization:[],
				orgNo:"532325",
				name:"姚安县"
			}, {
				organization:[],
				orgNo:"532326",
				name:"大姚县"
			}, {
				organization:[],
				orgNo:"532327",
				name:"永仁县"
			}, {
				organization:[],
				orgNo:"532328",
				name:"元谋县"
			}, {
				organization:[],
				orgNo:"532329",
				name:"武定县"
			}, {
				organization:[],
				orgNo:"532331",
				name:"禄丰县"
			} ],
			orgNo:"5323",
			name:"楚雄彝族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"532501",
				name:"个旧市"
			}, {
				organization:[],
				orgNo:"532502",
				name:"开远市"
			}, {
				organization:[],
				orgNo:"532522",
				name:"蒙自县"
			}, {
				organization:[],
				orgNo:"532523",
				name:"屏边苗族自治县"
			}, {
				organization:[],
				orgNo:"532524",
				name:"建水县"
			}, {
				organization:[],
				orgNo:"532525",
				name:"石屏县"
			}, {
				organization:[],
				orgNo:"532526",
				name:"弥勒县"
			}, {
				organization:[],
				orgNo:"532527",
				name:"泸西县"
			}, {
				organization:[],
				orgNo:"532528",
				name:"元阳县"
			}, {
				organization:[],
				orgNo:"532529",
				name:"红河县"
			}, {
				organization:[],
				orgNo:"532530",
				name:"金平苗族瑶族傣族自治县"
			}, {
				organization:[],
				orgNo:"532531",
				name:"绿春县"
			}, {
				organization:[],
				orgNo:"532532",
				name:"河口瑶族自治县"
			} ],
			orgNo:"5325",
			name:"红河哈尼族彝族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"532621",
				name:"文山县"
			}, {
				organization:[],
				orgNo:"532622",
				name:"砚山县"
			}, {
				organization:[],
				orgNo:"532623",
				name:"西畴县"
			}, {
				organization:[],
				orgNo:"532624",
				name:"麻栗坡县"
			}, {
				organization:[],
				orgNo:"532625",
				name:"马关县"
			}, {
				organization:[],
				orgNo:"532626",
				name:"丘北县"
			}, {
				organization:[],
				orgNo:"532627",
				name:"广南县"
			}, {
				organization:[],
				orgNo:"532628",
				name:"富宁县"
			} ],
			orgNo:"5326",
			name:"文山壮族苗族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"532801",
				name:"景洪市"
			}, {
				organization:[],
				orgNo:"532822",
				name:"勐海县"
			}, {
				organization:[],
				orgNo:"532823",
				name:"勐腊县"
			} ],
			orgNo:"5328",
			name:"西双版纳傣族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"532901",
				name:"大理市"
			}, {
				organization:[],
				orgNo:"532922",
				name:"漾濞彝族自治县"
			}, {
				organization:[],
				orgNo:"532923",
				name:"祥云县"
			}, {
				organization:[],
				orgNo:"532924",
				name:"宾川县"
			}, {
				organization:[],
				orgNo:"532925",
				name:"弥渡县"
			}, {
				organization:[],
				orgNo:"532926",
				name:"南涧彝族自治县"
			}, {
				organization:[],
				orgNo:"532927",
				name:"巍山彝族回族自治县"
			}, {
				organization:[],
				orgNo:"532928",
				name:"永平县"
			}, {
				organization:[],
				orgNo:"532929",
				name:"云龙县"
			}, {
				organization:[],
				orgNo:"532930",
				name:"洱源县"
			}, {
				organization:[],
				orgNo:"532931",
				name:"剑川县"
			}, {
				organization:[],
				orgNo:"532932",
				name:"鹤庆县"
			} ],
			orgNo:"5329",
			name:"大理白族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"533102",
				name:"瑞丽市"
			}, {
				organization:[],
				orgNo:"533103",
				name:"潞西市"
			}, {
				organization:[],
				orgNo:"533122",
				name:"梁河县"
			}, {
				organization:[],
				orgNo:"533123",
				name:"盈江县"
			}, {
				organization:[],
				orgNo:"533124",
				name:"陇川县"
			} ],
			orgNo:"5331",
			name:"德宏傣族景颇族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"533321",
				name:"泸水县"
			}, {
				organization:[],
				orgNo:"533323",
				name:"福贡县"
			}, {
				organization:[],
				orgNo:"533324",
				name:"贡山独龙族怒族自治县"
			}, {
				organization:[],
				orgNo:"533325",
				name:"兰坪白族普米族自治县"
			} ],
			orgNo:"5333",
			name:"怒江傈僳族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"533421",
				name:"香格里拉县"
			}, {
				organization:[],
				orgNo:"533422",
				name:"德钦县"
			}, {
				organization:[],
				orgNo:"533423",
				name:"维西傈僳族自治县"
			} ],
			orgNo:"5334",
			name:"迪庆藏族自治州"
		} ],
		orgNo:"53",
		name:"云南"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"540102",
				name:"城关区"
			}, {
				organization:[],
				orgNo:"540121",
				name:"林周县"
			}, {
				organization:[],
				orgNo:"540122",
				name:"当雄县"
			}, {
				organization:[],
				orgNo:"540123",
				name:"尼木县"
			}, {
				organization:[],
				orgNo:"540124",
				name:"曲水县"
			}, {
				organization:[],
				orgNo:"540125",
				name:"堆龙德庆县"
			}, {
				organization:[],
				orgNo:"540126",
				name:"达孜县"
			}, {
				organization:[],
				orgNo:"540127",
				name:"墨竹工卡县"
			} ],
			orgNo:"5401",
			name:"拉萨市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"542121",
				name:"昌都县"
			}, {
				organization:[],
				orgNo:"542122",
				name:"江达县"
			}, {
				organization:[],
				orgNo:"542123",
				name:"贡觉县"
			}, {
				organization:[],
				orgNo:"542124",
				name:"类乌齐县"
			}, {
				organization:[],
				orgNo:"542125",
				name:"丁青县"
			}, {
				organization:[],
				orgNo:"542126",
				name:"察雅县"
			}, {
				organization:[],
				orgNo:"542127",
				name:"八宿县"
			}, {
				organization:[],
				orgNo:"542128",
				name:"左贡县"
			}, {
				organization:[],
				orgNo:"542129",
				name:"芒康县"
			}, {
				organization:[],
				orgNo:"542132",
				name:"洛隆县"
			}, {
				organization:[],
				orgNo:"542133",
				name:"边坝县"
			} ],
			orgNo:"5421",
			name:"昌都地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"542221",
				name:"乃东县"
			}, {
				organization:[],
				orgNo:"542222",
				name:"扎囊县"
			}, {
				organization:[],
				orgNo:"542223",
				name:"贡嘎县"
			}, {
				organization:[],
				orgNo:"542224",
				name:"桑日县"
			}, {
				organization:[],
				orgNo:"542225",
				name:"琼结县"
			}, {
				organization:[],
				orgNo:"542226",
				name:"曲松县"
			}, {
				organization:[],
				orgNo:"542227",
				name:"措美县"
			}, {
				organization:[],
				orgNo:"542228",
				name:"洛扎县"
			}, {
				organization:[],
				orgNo:"542229",
				name:"加查县"
			}, {
				organization:[],
				orgNo:"542231",
				name:"隆子县"
			}, {
				organization:[],
				orgNo:"542232",
				name:"错那县"
			}, {
				organization:[],
				orgNo:"542233",
				name:"浪卡子县"
			} ],
			orgNo:"5422",
			name:"山南地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"542301",
				name:"日喀则市"
			}, {
				organization:[],
				orgNo:"542322",
				name:"南木林县"
			}, {
				organization:[],
				orgNo:"542323",
				name:"江孜县"
			}, {
				organization:[],
				orgNo:"542324",
				name:"定日县"
			}, {
				organization:[],
				orgNo:"542325",
				name:"萨迦县"
			}, {
				organization:[],
				orgNo:"542326",
				name:"拉孜县"
			}, {
				organization:[],
				orgNo:"542327",
				name:"昂仁县"
			}, {
				organization:[],
				orgNo:"542328",
				name:"谢通门县"
			}, {
				organization:[],
				orgNo:"542329",
				name:"白朗县"
			}, {
				organization:[],
				orgNo:"542330",
				name:"仁布县"
			}, {
				organization:[],
				orgNo:"542331",
				name:"康马县"
			}, {
				organization:[],
				orgNo:"542332",
				name:"定结县"
			}, {
				organization:[],
				orgNo:"542333",
				name:"仲巴县"
			}, {
				organization:[],
				orgNo:"542334",
				name:"亚东县"
			}, {
				organization:[],
				orgNo:"542335",
				name:"吉隆县"
			}, {
				organization:[],
				orgNo:"542336",
				name:"聂拉木县"
			}, {
				organization:[],
				orgNo:"542337",
				name:"萨嘎县"
			}, {
				organization:[],
				orgNo:"542338",
				name:"岗巴县"
			} ],
			orgNo:"5423",
			name:"日喀则地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"542421",
				name:"那曲县"
			}, {
				organization:[],
				orgNo:"542422",
				name:"嘉黎县"
			}, {
				organization:[],
				orgNo:"542423",
				name:"比如县"
			}, {
				organization:[],
				orgNo:"542424",
				name:"聂荣县"
			}, {
				organization:[],
				orgNo:"542425",
				name:"安多县"
			}, {
				organization:[],
				orgNo:"542426",
				name:"申扎县"
			}, {
				organization:[],
				orgNo:"542427",
				name:"索县"
			}, {
				organization:[],
				orgNo:"542428",
				name:"班戈县"
			}, {
				organization:[],
				orgNo:"542429",
				name:"巴青县"
			}, {
				organization:[],
				orgNo:"542430",
				name:"尼玛县"
			} ],
			orgNo:"5424",
			name:"那曲地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"542521",
				name:"普兰县"
			}, {
				organization:[],
				orgNo:"542522",
				name:"札达县"
			}, {
				organization:[],
				orgNo:"542523",
				name:"噶尔县"
			}, {
				organization:[],
				orgNo:"542524",
				name:"日土县"
			}, {
				organization:[],
				orgNo:"542525",
				name:"革吉县"
			}, {
				organization:[],
				orgNo:"542526",
				name:"改则县"
			}, {
				organization:[],
				orgNo:"542527",
				name:"措勤县"
			} ],
			orgNo:"5425",
			name:"阿里地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"542621",
				name:"林芝县"
			}, {
				organization:[],
				orgNo:"542622",
				name:"工布江达县"
			}, {
				organization:[],
				orgNo:"542623",
				name:"米林县"
			}, {
				organization:[],
				orgNo:"542624",
				name:"墨脱县"
			}, {
				organization:[],
				orgNo:"542625",
				name:"波密县"
			}, {
				organization:[],
				orgNo:"542626",
				name:"察隅县"
			}, {
				organization:[],
				orgNo:"542627",
				name:"朗县"
			} ],
			orgNo:"5426",
			name:"林芝地区"
		} ],
		orgNo:"54",
		name:"西藏"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"610102",
				name:"新城区"
			}, {
				organization:[],
				orgNo:"610103",
				name:"碑林区"
			}, {
				organization:[],
				orgNo:"610104",
				name:"莲湖区"
			}, {
				organization:[],
				orgNo:"610111",
				name:"灞桥区"
			}, {
				organization:[],
				orgNo:"610112",
				name:"未央区"
			}, {
				organization:[],
				orgNo:"610113",
				name:"雁塔区"
			}, {
				organization:[],
				orgNo:"610114",
				name:"阎良区"
			}, {
				organization:[],
				orgNo:"610115",
				name:"临潼区"
			}, {
				organization:[],
				orgNo:"610116",
				name:"长安区"
			}, {
				organization:[],
				orgNo:"610122",
				name:"蓝田县"
			}, {
				organization:[],
				orgNo:"610124",
				name:"周至县"
			}, {
				organization:[],
				orgNo:"610125",
				name:"户县"
			}, {
				organization:[],
				orgNo:"610126",
				name:"高陵县"
			}, {
				organization:[],
				orgNo:"610127",
				name:"高新区"
			} ],
			orgNo:"6101",
			name:"西安市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"610202",
				name:"王益区"
			}, {
				organization:[],
				orgNo:"610203",
				name:"印台区"
			}, {
				organization:[],
				orgNo:"610204",
				name:"耀州区"
			}, {
				organization:[],
				orgNo:"610222",
				name:"宜君县"
			} ],
			orgNo:"6102",
			name:"铜川市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"610302",
				name:"渭滨区"
			}, {
				organization:[],
				orgNo:"610303",
				name:"金台区"
			}, {
				organization:[],
				orgNo:"610304",
				name:"陈仓区"
			}, {
				organization:[],
				orgNo:"610322",
				name:"凤翔县"
			}, {
				organization:[],
				orgNo:"610323",
				name:"岐山县"
			}, {
				organization:[],
				orgNo:"610324",
				name:"扶风县"
			}, {
				organization:[],
				orgNo:"610326",
				name:"眉县"
			}, {
				organization:[],
				orgNo:"610327",
				name:"陇县"
			}, {
				organization:[],
				orgNo:"610328",
				name:"千阳县"
			}, {
				organization:[],
				orgNo:"610329",
				name:"麟游县"
			}, {
				organization:[],
				orgNo:"610330",
				name:"凤县"
			}, {
				organization:[],
				orgNo:"610331",
				name:"太白县"
			} ],
			orgNo:"6103",
			name:"宝鸡市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"610402",
				name:"秦都区"
			}, {
				organization:[],
				orgNo:"610403",
				name:"杨凌区"
			}, {
				organization:[],
				orgNo:"610404",
				name:"渭城区"
			}, {
				organization:[],
				orgNo:"610422",
				name:"三原县"
			}, {
				organization:[],
				orgNo:"610423",
				name:"泾阳县"
			}, {
				organization:[],
				orgNo:"610424",
				name:"乾县"
			}, {
				organization:[],
				orgNo:"610425",
				name:"礼泉县"
			}, {
				organization:[],
				orgNo:"610426",
				name:"永寿县"
			}, {
				organization:[],
				orgNo:"610427",
				name:"彬县"
			}, {
				organization:[],
				orgNo:"610428",
				name:"长武县"
			}, {
				organization:[],
				orgNo:"610429",
				name:"旬邑县"
			}, {
				organization:[],
				orgNo:"610430",
				name:"淳化县"
			}, {
				organization:[],
				orgNo:"610431",
				name:"武功县"
			}, {
				organization:[],
				orgNo:"610481",
				name:"兴平市"
			} ],
			orgNo:"6104",
			name:"咸阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"610502",
				name:"临渭区"
			}, {
				organization:[],
				orgNo:"610521",
				name:"华县"
			}, {
				organization:[],
				orgNo:"610522",
				name:"潼关县"
			}, {
				organization:[],
				orgNo:"610523",
				name:"大荔县"
			}, {
				organization:[],
				orgNo:"610524",
				name:"合阳县"
			}, {
				organization:[],
				orgNo:"610525",
				name:"澄城县"
			}, {
				organization:[],
				orgNo:"610526",
				name:"蒲城县"
			}, {
				organization:[],
				orgNo:"610527",
				name:"白水县"
			}, {
				organization:[],
				orgNo:"610528",
				name:"富平县"
			}, {
				organization:[],
				orgNo:"610581",
				name:"韩城市"
			}, {
				organization:[],
				orgNo:"610582",
				name:"华阴市"
			} ],
			orgNo:"6105",
			name:"渭南市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"610602",
				name:"宝塔区"
			}, {
				organization:[],
				orgNo:"610621",
				name:"延长县"
			}, {
				organization:[],
				orgNo:"610622",
				name:"延川县"
			}, {
				organization:[],
				orgNo:"610623",
				name:"子长县"
			}, {
				organization:[],
				orgNo:"610624",
				name:"安塞县"
			}, {
				organization:[],
				orgNo:"610625",
				name:"志丹县"
			}, {
				organization:[],
				orgNo:"610626",
				name:"吴起县"
			}, {
				organization:[],
				orgNo:"610627",
				name:"甘泉县"
			}, {
				organization:[],
				orgNo:"610628",
				name:"富县"
			}, {
				organization:[],
				orgNo:"610629",
				name:"洛川县"
			}, {
				organization:[],
				orgNo:"610630",
				name:"宜川县"
			}, {
				organization:[],
				orgNo:"610631",
				name:"黄龙县"
			}, {
				organization:[],
				orgNo:"610632",
				name:"黄陵县"
			} ],
			orgNo:"6106",
			name:"延安市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"610702",
				name:"汉台区"
			}, {
				organization:[],
				orgNo:"610721",
				name:"南郑县"
			}, {
				organization:[],
				orgNo:"610722",
				name:"城固县"
			}, {
				organization:[],
				orgNo:"610723",
				name:"洋县"
			}, {
				organization:[],
				orgNo:"610724",
				name:"西乡县"
			}, {
				organization:[],
				orgNo:"610725",
				name:"勉县"
			}, {
				organization:[],
				orgNo:"610726",
				name:"宁强县"
			}, {
				organization:[],
				orgNo:"610727",
				name:"略阳县"
			}, {
				organization:[],
				orgNo:"610728",
				name:"镇巴县"
			}, {
				organization:[],
				orgNo:"610729",
				name:"留坝县"
			}, {
				organization:[],
				orgNo:"610730",
				name:"佛坪县"
			} ],
			orgNo:"6107",
			name:"汉中市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"610802",
				name:"榆阳区"
			}, {
				organization:[],
				orgNo:"610821",
				name:"神木县"
			}, {
				organization:[],
				orgNo:"610822",
				name:"府谷县"
			}, {
				organization:[],
				orgNo:"610823",
				name:"横山县"
			}, {
				organization:[],
				orgNo:"610824",
				name:"靖边县"
			}, {
				organization:[],
				orgNo:"610825",
				name:"定边县"
			}, {
				organization:[],
				orgNo:"610826",
				name:"绥德县"
			}, {
				organization:[],
				orgNo:"610827",
				name:"米脂县"
			}, {
				organization:[],
				orgNo:"610828",
				name:"佳县"
			}, {
				organization:[],
				orgNo:"610829",
				name:"吴堡县"
			}, {
				organization:[],
				orgNo:"610830",
				name:"清涧县"
			}, {
				organization:[],
				orgNo:"610831",
				name:"子洲县"
			} ],
			orgNo:"6108",
			name:"榆林市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"610902",
				name:"汉滨区"
			}, {
				organization:[],
				orgNo:"610921",
				name:"汉阴县"
			}, {
				organization:[],
				orgNo:"610922",
				name:"石泉县"
			}, {
				organization:[],
				orgNo:"610923",
				name:"宁陕县"
			}, {
				organization:[],
				orgNo:"610924",
				name:"紫阳县"
			}, {
				organization:[],
				orgNo:"610925",
				name:"岚皋县"
			}, {
				organization:[],
				orgNo:"610926",
				name:"平利县"
			}, {
				organization:[],
				orgNo:"610927",
				name:"镇坪县"
			}, {
				organization:[],
				orgNo:"610928",
				name:"旬阳县"
			}, {
				organization:[],
				orgNo:"610929",
				name:"白河县"
			} ],
			orgNo:"6109",
			name:"安康市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"611002",
				name:"商州区"
			}, {
				organization:[],
				orgNo:"611021",
				name:"洛南县"
			}, {
				organization:[],
				orgNo:"611022",
				name:"丹凤县"
			}, {
				organization:[],
				orgNo:"611023",
				name:"商南县"
			}, {
				organization:[],
				orgNo:"611024",
				name:"山阳县"
			}, {
				organization:[],
				orgNo:"611025",
				name:"镇安县"
			}, {
				organization:[],
				orgNo:"611026",
				name:"柞水县"
			} ],
			orgNo:"6110",
			name:"商洛市"
		} ],
		orgNo:"61",
		name:"陕西"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"620102",
				name:"城关区"
			}, {
				organization:[],
				orgNo:"620103",
				name:"七里河区"
			}, {
				organization:[],
				orgNo:"620104",
				name:"西固区"
			}, {
				organization:[],
				orgNo:"620105",
				name:"安宁区"
			}, {
				organization:[],
				orgNo:"620111",
				name:"红古区"
			}, {
				organization:[],
				orgNo:"620121",
				name:"永登县"
			}, {
				organization:[],
				orgNo:"620122",
				name:"皋兰县"
			}, {
				organization:[],
				orgNo:"620123",
				name:"榆中县"
			} ],
			orgNo:"6201",
			name:"兰州市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"620200",
				name:"嘉峪关市"
			} ],
			orgNo:"6202",
			name:"嘉峪关市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"620302",
				name:"金川区"
			}, {
				organization:[],
				orgNo:"620321",
				name:"永昌县"
			} ],
			orgNo:"6203",
			name:"金昌市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"620402",
				name:"白银区"
			}, {
				organization:[],
				orgNo:"620403",
				name:"平川区"
			}, {
				organization:[],
				orgNo:"620421",
				name:"靖远县"
			}, {
				organization:[],
				orgNo:"620422",
				name:"会宁县"
			}, {
				organization:[],
				orgNo:"620423",
				name:"景泰县"
			} ],
			orgNo:"6204",
			name:"白银市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"620502",
				name:"秦州区"
			}, {
				organization:[],
				orgNo:"620503",
				name:"麦积区"
			}, {
				organization:[],
				orgNo:"620521",
				name:"清水县"
			}, {
				organization:[],
				orgNo:"620522",
				name:"秦安县"
			}, {
				organization:[],
				orgNo:"620523",
				name:"甘谷县"
			}, {
				organization:[],
				orgNo:"620524",
				name:"武山县"
			}, {
				organization:[],
				orgNo:"620525",
				name:"张家川回族自治县"
			} ],
			orgNo:"6205",
			name:"天水市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"620602",
				name:"凉州区"
			}, {
				organization:[],
				orgNo:"620621",
				name:"民勤县"
			}, {
				organization:[],
				orgNo:"620622",
				name:"古浪县"
			}, {
				organization:[],
				orgNo:"620623",
				name:"天祝藏族自治县"
			} ],
			orgNo:"6206",
			name:"武威市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"620702",
				name:"甘州区"
			}, {
				organization:[],
				orgNo:"620721",
				name:"肃南裕固族自治县"
			}, {
				organization:[],
				orgNo:"620722",
				name:"民乐县"
			}, {
				organization:[],
				orgNo:"620723",
				name:"临泽县"
			}, {
				organization:[],
				orgNo:"620724",
				name:"高台县"
			}, {
				organization:[],
				orgNo:"620725",
				name:"山丹县"
			} ],
			orgNo:"6207",
			name:"张掖市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"620802",
				name:"崆峒区"
			}, {
				organization:[],
				orgNo:"620821",
				name:"泾川县"
			}, {
				organization:[],
				orgNo:"620822",
				name:"灵台县"
			}, {
				organization:[],
				orgNo:"620823",
				name:"崇信县"
			}, {
				organization:[],
				orgNo:"620824",
				name:"华亭县"
			}, {
				organization:[],
				orgNo:"620825",
				name:"庄浪县"
			}, {
				organization:[],
				orgNo:"620826",
				name:"静宁县"
			} ],
			orgNo:"6208",
			name:"平凉市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"620902",
				name:"肃州区"
			}, {
				organization:[],
				orgNo:"620921",
				name:"金塔县"
			}, {
				organization:[],
				orgNo:"620922",
				name:"瓜州县"
			}, {
				organization:[],
				orgNo:"620923",
				name:"肃北蒙古族自治县"
			}, {
				organization:[],
				orgNo:"620924",
				name:"阿克塞哈萨克族自治县"
			}, {
				organization:[],
				orgNo:"620981",
				name:"玉门市"
			}, {
				organization:[],
				orgNo:"620982",
				name:"敦煌市"
			} ],
			orgNo:"6209",
			name:"酒泉市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"621002",
				name:"西峰区"
			}, {
				organization:[],
				orgNo:"621021",
				name:"庆城县"
			}, {
				organization:[],
				orgNo:"621022",
				name:"环县"
			}, {
				organization:[],
				orgNo:"621023",
				name:"华池县"
			}, {
				organization:[],
				orgNo:"621024",
				name:"合水县"
			}, {
				organization:[],
				orgNo:"621025",
				name:"正宁县"
			}, {
				organization:[],
				orgNo:"621026",
				name:"宁县"
			}, {
				organization:[],
				orgNo:"621027",
				name:"镇原县"
			} ],
			orgNo:"6210",
			name:"庆阳市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"621102",
				name:"安定区"
			}, {
				organization:[],
				orgNo:"621121",
				name:"通渭县"
			}, {
				organization:[],
				orgNo:"621122",
				name:"陇西县"
			}, {
				organization:[],
				orgNo:"621123",
				name:"渭源县"
			}, {
				organization:[],
				orgNo:"621124",
				name:"临洮县"
			}, {
				organization:[],
				orgNo:"621125",
				name:"漳县"
			}, {
				organization:[],
				orgNo:"621126",
				name:"岷县"
			} ],
			orgNo:"6211",
			name:"定西市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"621202",
				name:"武都区"
			}, {
				organization:[],
				orgNo:"621221",
				name:"成县"
			}, {
				organization:[],
				orgNo:"621222",
				name:"文县"
			}, {
				organization:[],
				orgNo:"621223",
				name:"宕昌县"
			}, {
				organization:[],
				orgNo:"621224",
				name:"康县"
			}, {
				organization:[],
				orgNo:"621225",
				name:"西和县"
			}, {
				organization:[],
				orgNo:"621226",
				name:"礼县"
			}, {
				organization:[],
				orgNo:"621227",
				name:"徽县"
			}, {
				organization:[],
				orgNo:"621228",
				name:"两当县"
			} ],
			orgNo:"6212",
			name:"陇南市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"622901",
				name:"临夏市"
			}, {
				organization:[],
				orgNo:"622921",
				name:"临夏县"
			}, {
				organization:[],
				orgNo:"622922",
				name:"康乐县"
			}, {
				organization:[],
				orgNo:"622923",
				name:"永靖县"
			}, {
				organization:[],
				orgNo:"622924",
				name:"广河县"
			}, {
				organization:[],
				orgNo:"622925",
				name:"和政县"
			}, {
				organization:[],
				orgNo:"622926",
				name:"东乡族自治县"
			}, {
				organization:[],
				orgNo:"622927",
				name:"积石山保安族东乡族撒拉族自治县"
			} ],
			orgNo:"6229",
			name:"临夏回族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"623001",
				name:"合作市"
			}, {
				organization:[],
				orgNo:"623021",
				name:"临潭县"
			}, {
				organization:[],
				orgNo:"623022",
				name:"卓尼县"
			}, {
				organization:[],
				orgNo:"623023",
				name:"舟曲县"
			}, {
				organization:[],
				orgNo:"623024",
				name:"迭部县"
			}, {
				organization:[],
				orgNo:"623025",
				name:"玛曲县"
			}, {
				organization:[],
				orgNo:"623026",
				name:"碌曲县"
			}, {
				organization:[],
				orgNo:"623027",
				name:"夏河县"
			} ],
			orgNo:"6230",
			name:"甘南藏族自治州"
		} ],
		orgNo:"62",
		name:"甘肃"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"630102",
				name:"城东区"
			}, {
				organization:[],
				orgNo:"630103",
				name:"城中区"
			}, {
				organization:[],
				orgNo:"630104",
				name:"城西区"
			}, {
				organization:[],
				orgNo:"630105",
				name:"城北区"
			}, {
				organization:[],
				orgNo:"630121",
				name:"大通回族土族自治县"
			}, {
				organization:[],
				orgNo:"630122",
				name:"湟中县"
			}, {
				organization:[],
				orgNo:"630123",
				name:"湟源县"
			} ],
			orgNo:"6301",
			name:"西宁市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"632121",
				name:"平安县"
			}, {
				organization:[],
				orgNo:"632122",
				name:"民和回族土族自治县"
			}, {
				organization:[],
				orgNo:"632123",
				name:"乐都县"
			}, {
				organization:[],
				orgNo:"632126",
				name:"互助土族自治县"
			}, {
				organization:[],
				orgNo:"632127",
				name:"化隆回族自治县"
			}, {
				organization:[],
				orgNo:"632128",
				name:"循化撒拉族自治县"
			} ],
			orgNo:"6321",
			name:"海东地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"632221",
				name:"门源回族自治县"
			}, {
				organization:[],
				orgNo:"632222",
				name:"祁连县"
			}, {
				organization:[],
				orgNo:"632223",
				name:"海晏县"
			}, {
				organization:[],
				orgNo:"632224",
				name:"刚察县"
			} ],
			orgNo:"6322",
			name:"海北藏族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"632321",
				name:"同仁县"
			}, {
				organization:[],
				orgNo:"632322",
				name:"尖扎县"
			}, {
				organization:[],
				orgNo:"632323",
				name:"泽库县"
			}, {
				organization:[],
				orgNo:"632324",
				name:"河南蒙古族自治县"
			} ],
			orgNo:"6323",
			name:"黄南藏族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"632521",
				name:"共和县"
			}, {
				organization:[],
				orgNo:"632522",
				name:"同德县"
			}, {
				organization:[],
				orgNo:"632523",
				name:"贵德县"
			}, {
				organization:[],
				orgNo:"632524",
				name:"兴海县"
			}, {
				organization:[],
				orgNo:"632525",
				name:"贵南县"
			} ],
			orgNo:"6325",
			name:"海南藏族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"632621",
				name:"玛沁县"
			}, {
				organization:[],
				orgNo:"632622",
				name:"班玛县"
			}, {
				organization:[],
				orgNo:"632623",
				name:"甘德县"
			}, {
				organization:[],
				orgNo:"632624",
				name:"达日县"
			}, {
				organization:[],
				orgNo:"632625",
				name:"久治县"
			}, {
				organization:[],
				orgNo:"632626",
				name:"玛多县"
			} ],
			orgNo:"6326",
			name:"果洛藏族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"632721",
				name:"玉树县"
			}, {
				organization:[],
				orgNo:"632722",
				name:"杂多县"
			}, {
				organization:[],
				orgNo:"632723",
				name:"称多县"
			}, {
				organization:[],
				orgNo:"632724",
				name:"治多县"
			}, {
				organization:[],
				orgNo:"632725",
				name:"囊谦县"
			}, {
				organization:[],
				orgNo:"632726",
				name:"曲麻莱县"
			} ],
			orgNo:"6327",
			name:"玉树藏族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"632801",
				name:"格尔木市"
			}, {
				organization:[],
				orgNo:"632802",
				name:"德令哈市"
			}, {
				organization:[],
				orgNo:"632821",
				name:"乌兰县"
			}, {
				organization:[],
				orgNo:"632822",
				name:"都兰县"
			}, {
				organization:[],
				orgNo:"632823",
				name:"天峻县"
			} ],
			orgNo:"6328",
			name:"海西蒙古族藏族自治州"
		} ],
		orgNo:"63",
		name:"青海"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"640104",
				name:"兴庆区"
			}, {
				organization:[],
				orgNo:"640105",
				name:"西夏区"
			}, {
				organization:[],
				orgNo:"640106",
				name:"金凤区"
			}, {
				organization:[],
				orgNo:"640121",
				name:"永宁县"
			}, {
				organization:[],
				orgNo:"640122",
				name:"贺兰县"
			}, {
				organization:[],
				orgNo:"640181",
				name:"灵武市"
			} ],
			orgNo:"6401",
			name:"银川市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"640202",
				name:"大武口区"
			}, {
				organization:[],
				orgNo:"640205",
				name:"惠农区"
			}, {
				organization:[],
				orgNo:"640221",
				name:"平罗县"
			} ],
			orgNo:"6402",
			name:"石嘴山市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"640302",
				name:"利通区"
			}, {
				organization:[],
				orgNo:"640323",
				name:"盐池县"
			}, {
				organization:[],
				orgNo:"640324",
				name:"同心县"
			}, {
				organization:[],
				orgNo:"640381",
				name:"青铜峡市"
			} ],
			orgNo:"6403",
			name:"吴忠市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"640402",
				name:"原州区"
			}, {
				organization:[],
				orgNo:"640422",
				name:"西吉县"
			}, {
				organization:[],
				orgNo:"640423",
				name:"隆德县"
			}, {
				organization:[],
				orgNo:"640424",
				name:"泾源县"
			}, {
				organization:[],
				orgNo:"640425",
				name:"彭阳县"
			} ],
			orgNo:"6404",
			name:"固原市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"640502",
				name:"沙坡头区"
			}, {
				organization:[],
				orgNo:"640521",
				name:"中宁县"
			}, {
				organization:[],
				orgNo:"640522",
				name:"海原县"
			} ],
			orgNo:"6405",
			name:"中卫市"
		} ],
		orgNo:"64",
		name:"宁夏"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"650102",
				name:"天山区"
			}, {
				organization:[],
				orgNo:"650103",
				name:"沙依巴克区"
			}, {
				organization:[],
				orgNo:"650104",
				name:"新市区"
			}, {
				organization:[],
				orgNo:"650105",
				name:"水磨沟区"
			}, {
				organization:[],
				orgNo:"650106",
				name:"头屯河区"
			}, {
				organization:[],
				orgNo:"650107",
				name:"达坂城区"
			}, {
				organization:[],
				orgNo:"650108",
				name:"米东区"
			}, {
				organization:[],
				orgNo:"650121",
				name:"乌鲁木齐县"
			}, {
				organization:[],
				orgNo:"650123",
				name:"兵团"
			} ],
			orgNo:"6501",
			name:"乌鲁木齐市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"650202",
				name:"独山子区"
			}, {
				organization:[],
				orgNo:"650203",
				name:"克拉玛依区"
			}, {
				organization:[],
				orgNo:"650204",
				name:"白碱滩区"
			}, {
				organization:[],
				orgNo:"650205",
				name:"乌尔禾区"
			}, {
				organization:[],
				orgNo:"650206",
				name:"兵团"
			} ],
			orgNo:"6502",
			name:"克拉玛依市"
		}, {
			organization:[ {
				organization:[],
				orgNo:"652101",
				name:"吐鲁番市"
			}, {
				organization:[],
				orgNo:"652122",
				name:"鄯善县"
			}, {
				organization:[],
				orgNo:"652123",
				name:"托克逊县"
			}, {
				organization:[],
				orgNo:"652124",
				name:"兵团"
			} ],
			orgNo:"6521",
			name:"吐鲁番地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"652201",
				name:"哈密市"
			}, {
				organization:[],
				orgNo:"652222",
				name:"巴里坤哈萨克自治县"
			}, {
				organization:[],
				orgNo:"652223",
				name:"伊吾县"
			}, {
				organization:[],
				orgNo:"652224",
				name:"兵团"
			} ],
			orgNo:"6522",
			name:"哈密地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"652301",
				name:"昌吉市"
			}, {
				organization:[],
				orgNo:"652302",
				name:"阜康市"
			}, {
				organization:[],
				orgNo:"652303",
				name:"米泉市"
			}, {
				organization:[],
				orgNo:"652323",
				name:"呼图壁县"
			}, {
				organization:[],
				orgNo:"652324",
				name:"玛纳斯县"
			}, {
				organization:[],
				orgNo:"652325",
				name:"奇台县"
			}, {
				organization:[],
				orgNo:"652327",
				name:"吉木萨尔县"
			}, {
				organization:[],
				orgNo:"652328",
				name:"木垒哈萨克自治县"
			}, {
				organization:[],
				orgNo:"652329",
				name:"兵团"
			} ],
			orgNo:"6523",
			name:"昌吉回族自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"652701",
				name:"博乐市"
			}, {
				organization:[],
				orgNo:"652722",
				name:"精河县"
			}, {
				organization:[],
				orgNo:"652723",
				name:"温泉县"
			}, {
				organization:[],
				orgNo:"652724",
				name:"兵团"
			} ],
			orgNo:"6527",
			name:"博尔塔拉蒙古自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"652801",
				name:"库尔勒市"
			}, {
				organization:[],
				orgNo:"652822",
				name:"轮台县"
			}, {
				organization:[],
				orgNo:"652823",
				name:"尉犁县"
			}, {
				organization:[],
				orgNo:"652824",
				name:"若羌县"
			}, {
				organization:[],
				orgNo:"652825",
				name:"且末县"
			}, {
				organization:[],
				orgNo:"652826",
				name:"焉耆回族自治县"
			}, {
				organization:[],
				orgNo:"652827",
				name:"和静县"
			}, {
				organization:[],
				orgNo:"652828",
				name:"和硕县"
			}, {
				organization:[],
				orgNo:"652829",
				name:"博湖县"
			}, {
				organization:[],
				orgNo:"652830",
				name:"兵团"
			} ],
			orgNo:"6528",
			name:"巴音郭楞蒙古自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"652901",
				name:"阿克苏市"
			}, {
				organization:[],
				orgNo:"652922",
				name:"温宿县"
			}, {
				organization:[],
				orgNo:"652923",
				name:"库车县"
			}, {
				organization:[],
				orgNo:"652924",
				name:"沙雅县"
			}, {
				organization:[],
				orgNo:"652925",
				name:"新和县"
			}, {
				organization:[],
				orgNo:"652926",
				name:"拜城县"
			}, {
				organization:[],
				orgNo:"652927",
				name:"乌什县"
			}, {
				organization:[],
				orgNo:"652928",
				name:"阿瓦提县"
			}, {
				organization:[],
				orgNo:"652929",
				name:"柯坪县"
			}, {
				organization:[],
				orgNo:"652930",
				name:"兵团"
			} ],
			orgNo:"6529",
			name:"阿克苏地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"653001",
				name:"阿图什市"
			}, {
				organization:[],
				orgNo:"653022",
				name:"阿克陶县"
			}, {
				organization:[],
				orgNo:"653023",
				name:"阿合奇县"
			}, {
				organization:[],
				orgNo:"653024",
				name:"乌恰县"
			}, {
				organization:[],
				orgNo:"653025",
				name:"兵团"
			} ],
			orgNo:"6530",
			name:"克孜勒苏柯尔克孜自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"653101",
				name:"喀什市"
			}, {
				organization:[],
				orgNo:"653121",
				name:"疏附县"
			}, {
				organization:[],
				orgNo:"653122",
				name:"疏勒县"
			}, {
				organization:[],
				orgNo:"653123",
				name:"英吉沙县"
			}, {
				organization:[],
				orgNo:"653124",
				name:"泽普县"
			}, {
				organization:[],
				orgNo:"653125",
				name:"莎车县"
			}, {
				organization:[],
				orgNo:"653126",
				name:"叶城县"
			}, {
				organization:[],
				orgNo:"653127",
				name:"麦盖提县"
			}, {
				organization:[],
				orgNo:"653128",
				name:"岳普湖县"
			}, {
				organization:[],
				orgNo:"653129",
				name:"伽师县"
			}, {
				organization:[],
				orgNo:"653130",
				name:"巴楚县"
			}, {
				organization:[],
				orgNo:"653131",
				name:"塔什库尔干塔吉克自治县"
			}, {
				organization:[],
				orgNo:"653132",
				name:"兵团"
			} ],
			orgNo:"6531",
			name:"喀什地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"653201",
				name:"和田市"
			}, {
				organization:[],
				orgNo:"653221",
				name:"和田县"
			}, {
				organization:[],
				orgNo:"653222",
				name:"墨玉县"
			}, {
				organization:[],
				orgNo:"653223",
				name:"皮山县"
			}, {
				organization:[],
				orgNo:"653224",
				name:"洛浦县"
			}, {
				organization:[],
				orgNo:"653225",
				name:"策勒县"
			}, {
				organization:[],
				orgNo:"653226",
				name:"于田县"
			}, {
				organization:[],
				orgNo:"653227",
				name:"民丰县"
			}, {
				organization:[],
				orgNo:"653228",
				name:"兵团"
			} ],
			orgNo:"6532",
			name:"和田地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"654002",
				name:"伊宁市"
			}, {
				organization:[],
				orgNo:"654003",
				name:"奎屯市"
			}, {
				organization:[],
				orgNo:"654021",
				name:"伊宁县"
			}, {
				organization:[],
				orgNo:"654022",
				name:"察布查尔锡伯自治县"
			}, {
				organization:[],
				orgNo:"654023",
				name:"霍城县"
			}, {
				organization:[],
				orgNo:"654024",
				name:"巩留县"
			}, {
				organization:[],
				orgNo:"654025",
				name:"新源县"
			}, {
				organization:[],
				orgNo:"654026",
				name:"昭苏县"
			}, {
				organization:[],
				orgNo:"654027",
				name:"特克斯县"
			}, {
				organization:[],
				orgNo:"654028",
				name:"尼勒克县"
			} ],
			orgNo:"6540",
			name:"伊犁哈萨克自治州"
		}, {
			organization:[ {
				organization:[],
				orgNo:"654201",
				name:"塔城市"
			}, {
				organization:[],
				orgNo:"654202",
				name:"乌苏市"
			}, {
				organization:[],
				orgNo:"654221",
				name:"额敏县"
			}, {
				organization:[],
				orgNo:"654223",
				name:"沙湾县"
			}, {
				organization:[],
				orgNo:"654224",
				name:"托里县"
			}, {
				organization:[],
				orgNo:"654225",
				name:"裕民县"
			}, {
				organization:[],
				orgNo:"654226",
				name:"和布克赛尔蒙古自治县"
			}, {
				organization:[],
				orgNo:"654227",
				name:"兵团"
			} ],
			orgNo:"6542",
			name:"塔城地区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"654301",
				name:"阿勒泰市"
			}, {
				organization:[],
				orgNo:"654321",
				name:"布尔津县"
			}, {
				organization:[],
				orgNo:"654322",
				name:"富蕴县"
			}, {
				organization:[],
				orgNo:"654323",
				name:"福海县"
			}, {
				organization:[],
				orgNo:"654324",
				name:"哈巴河县"
			}, {
				organization:[],
				orgNo:"654325",
				name:"青河县"
			}, {
				organization:[],
				orgNo:"654326",
				name:"吉木乃县"
			}, {
				organization:[],
				orgNo:"654327",
				name:"兵团"
			} ],
			orgNo:"6543",
			name:"阿勒泰地区"
		}, {
			organization:[],
			orgNo:"659001",
			name:"石河子市"
		}, {
			organization:[],
			orgNo:"659002",
			name:"阿拉尔市"
		}, {
			organization:[],
			orgNo:"659003",
			name:"图木舒克市"
		}, {
			organization:[],
			orgNo:"659004",
			name:"五家渠市"
		} ],
		orgNo:"65",
		name:"新疆"
	}, {
		organization:[ {
			organization:[],
			orgNo:"7101",
			name:"台北市"
		}, {
			organization:[],
			orgNo:"7102",
			name:"高雄市"
		}, {
			organization:[],
			orgNo:"7103",
			name:"基隆市"
		}, {
			organization:[],
			orgNo:"7104",
			name:"台中市"
		}, {
			organization:[],
			orgNo:"7105",
			name:"台南市"
		}, {
			organization:[],
			orgNo:"7106",
			name:"新竹市"
		}, {
			organization:[],
			orgNo:"7107",
			name:"嘉义市"
		} ],
		orgNo:"71",
		name:"台湾"
	}, {
		organization:[ {
			organization:[ {
				organization:[],
				orgNo:"810101",
				name:"中西区"
			}, {
				organization:[],
				orgNo:"810102",
				name:"湾仔区"
			}, {
				organization:[],
				orgNo:"810103",
				name:"东区"
			}, {
				organization:[],
				orgNo:"810104",
				name:"南区"
			} ],
			orgNo:"8101",
			name:"中西区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"810201",
				name:"九龙城区"
			}, {
				organization:[],
				orgNo:"810202",
				name:"油尖旺区"
			}, {
				organization:[],
				orgNo:"810203",
				name:"观塘区"
			}, {
				organization:[],
				orgNo:"810204",
				name:"黄大仙区"
			}, {
				organization:[],
				orgNo:"810205",
				name:"深水埗区"
			} ],
			orgNo:"8102",
			name:"湾仔区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"810301",
				name:"北区"
			}, {
				organization:[],
				orgNo:"810302",
				name:"大埔区"
			}, {
				organization:[],
				orgNo:"810303",
				name:"西贡区"
			}, {
				organization:[],
				orgNo:"810304",
				name:"沙田区"
			} ],
			orgNo:"8103",
			name:"东区"
		}, {
			organization:[ {
				organization:[],
				orgNo:"810401",
				name:"元朗区"
			}, {
				organization:[],
				orgNo:"810402",
				name:"屯门区"
			}, {
				organization:[],
				orgNo:"810403",
				name:"荃湾区"
			}, {
				organization:[],
				orgNo:"810404",
				name:"葵青区"
			}, {
				organization:[],
				orgNo:"810405",
				name:"离岛区"
			} ],
			orgNo:"8104",
			name:"南区"
		}, {
			organization:[],
			orgNo:"8105",
			name:"油尖旺区"
		}, {
			organization:[],
			orgNo:"8106",
			name:"深水埗区"
		}, {
			organization:[],
			orgNo:"8107",
			name:"九龙城区"
		}, {
			organization:[],
			orgNo:"8108",
			name:"黄大仙区"
		}, {
			organization:[],
			orgNo:"8109",
			name:"观塘区"
		}, {
			organization:[],
			orgNo:"8110",
			name:"荃湾区"
		}, {
			organization:[],
			orgNo:"8111",
			name:"葵青区"
		}, {
			organization:[],
			orgNo:"8112",
			name:"沙田区"
		}, {
			organization:[],
			orgNo:"8113",
			name:"西贡区"
		}, {
			organization:[],
			orgNo:"8114",
			name:"大埔区"
		}, {
			organization:[],
			orgNo:"8115",
			name:"北区"
		}, {
			organization:[],
			orgNo:"8116",
			name:"元朗区"
		}, {
			organization:[],
			orgNo:"8117",
			name:"屯门区"
		}, {
			organization:[],
			orgNo:"8118",
			name:"离岛区"
		} ],
		orgNo:"81",
		name:"香港"
	}, {
		organization:[ {
			organization:[],
			orgNo:"8200",
			name:"澳门"
		} ],
		orgNo:"82",
		name:"澳门"
	} ];
	module.exports = citys;
});