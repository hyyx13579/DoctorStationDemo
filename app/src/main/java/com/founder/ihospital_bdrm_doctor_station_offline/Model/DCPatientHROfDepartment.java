package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 呼延 on 2016/3/29.
 * 方法名称	GetPatientHROfDepartment
 * 接口简介	获取科室在院患者记录
 * departmentID	科室ID，病区ID
 */
public class DCPatientHROfDepartment {
    /**
     * Status : 1
     * Values : [{"ID":"D257318","HID":"82392","CaseID":"D257318","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"2","DoctorInCharge":"刘洋","NurseInCharge":null,"Diagnosis":"腰椎骨折","Name":"黄淑英","NamePhonetic":"HUANG SHU YING","Nation":"汉族","Sex":"女","BirthDay":"1932/4/14 0:00:00","Age":"84","EnterDate":"2015/4/28 10:42:47","ExitDate":null,"MedicalInsuranceID":"10063643901S","IdentityType":"一般人员","Company":"北京象牙雕刻厂有限责任公司","Phone":null,"Address":"北京市丰台区嘉园二里4号楼4门102","ZipCode":"100062","LinkManName":"郑志惠","LinkManRelation":"子女","LinkManPhone":"13120011961","IdentityNumber":"11010319320414182X","Payment":"医疗保险","CurrentState":"一般","CareLevel":"一级护理","PrePayments":"23000.00","TotalCost":"76810.28","TotalPayments":"39535.28","BalanceMoney":null,"InHosDay":335,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D254744","HID":"81852","CaseID":"D254744","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"3","DoctorInCharge":"刘杨","NurseInCharge":null,"Diagnosis":"肌肉拉伤","Name":"任新会","NamePhonetic":"REN XIN HUI","Nation":"汉族","Sex":"男","BirthDay":"1948/5/11 0:00:00","Age":"68","EnterDate":"2015/4/2 8:55:02","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"河南孟津县麻屯镇教育组","Phone":null,"Address":"北京市丰台区成寿路11号","ZipCode":"100078","LinkManName":"任瑾玺","LinkManRelation":"子女","LinkManPhone":"13381222196","IdentityNumber":"410322194805119811","Payment":"异地保险","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"30000.00","TotalCost":"24058.35","TotalPayments":"24058.35","BalanceMoney":null,"InHosDay":362,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D256603","HID":"82218","CaseID":"D256603","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"4","DoctorInCharge":"田丰富","NurseInCharge":null,"Diagnosis":"股骨颈骨折","Name":"马同广","NamePhonetic":"MA TONG GUANG","Nation":"汉族","Sex":"男","BirthDay":"1971/7/25 0:00:00","Age":"45","EnterDate":"2015/4/20 8:50:43","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"无","Phone":null,"Address":"山西省大同市城区宋庄南路2号3楼2单元17号","ZipCode":"037006","LinkManName":"刘凤英","LinkManRelation":"夫妻","LinkManPhone":"13994386558","IdentityNumber":"140203197107250417","Payment":"异地保险","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"10000.00","TotalCost":"10354.89","TotalPayments":"9243.99","BalanceMoney":null,"InHosDay":344,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D257030","HID":"82349","CaseID":"D257030","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"5","DoctorInCharge":"刘洋","NurseInCharge":null,"Diagnosis":"颈椎病","Name":"张金兰","NamePhonetic":"ZHANG JIN LAN","Nation":"汉族","Sex":"女","BirthDay":"1945/11/25 0:00:00","Age":"71","EnterDate":"2015/4/26 16:48:21","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"河北省衡水故城祖阳农校","Phone":null,"Address":"河北省衡水市郑口镇翠景小区1-2-502","ZipCode":"012400","LinkManName":"郑爱新","LinkManRelation":"子女","LinkManPhone":"15832816387","IdentityNumber":"131126194511250640","Payment":"异地保险","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"80000.00","TotalCost":"3272.36","TotalPayments":"3272.36","BalanceMoney":null,"InHosDay":337,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D255040","HID":"82128","CaseID":"D255040","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"6","DoctorInCharge":"刘洋","NurseInCharge":null,"Diagnosis":"踝关节骨折","Name":"高悦","NamePhonetic":"GAO YUE","Nation":"汉族","Sex":"女","BirthDay":"1968/9/2 0:00:00","Age":"48","EnterDate":"2015/4/14 13:39:31","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"北京市雅美宏达义齿加工中心","Phone":null,"Address":"北京市石景山区古城南街88号4楼","ZipCode":"100000","LinkManName":"王芳","LinkManRelation":"亲属","LinkManPhone":"13089996268","IdentityNumber":"230102196809020048","Payment":"收费","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"25000.00","TotalCost":"35362.68","TotalPayments":"24652.68","BalanceMoney":null,"InHosDay":349,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D255937","HID":"82321","CaseID":"D255937","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"7","DoctorInCharge":"刘洋","NurseInCharge":null,"Diagnosis":"手术后恢复期","Name":"叶玉兰","NamePhonetic":"YE YU LAN","Nation":"汉族","Sex":"女","BirthDay":"1937/10/14 0:00:00","Age":"79","EnterDate":"2015/4/24 13:28:48","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"无","Phone":null,"Address":"北京市通州区张家湾镇垡头村806","ZipCode":"101100","LinkManName":"朱舍予","LinkManRelation":"子女","LinkManPhone":"13601060337","IdentityNumber":"330322193710144821","Payment":"收费","CurrentState":"一般","CareLevel":"一级护理","PrePayments":"10000.00","TotalCost":"5566.92","TotalPayments":"5566.92","BalanceMoney":null,"InHosDay":339,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D255568","HID":"82103","CaseID":"D255568","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"8","DoctorInCharge":"刘明","NurseInCharge":null,"Diagnosis":"膝骨关节炎","Name":"马春花","NamePhonetic":"MA CHUN HUA","Nation":"汉族","Sex":"女","BirthDay":"1956/2/25 0:00:00","Age":"60","EnterDate":"2015/4/14 8:08:37","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"无","Phone":null,"Address":"北京市东城区永定门西滨河路8号院7号楼中海","ZipCode":"100077","LinkManName":"马永胜","LinkManRelation":"(岳)父母","LinkManPhone":"13901210876","IdentityNumber":"142726195602253329","Payment":"异地保险","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"70000.00","TotalCost":"68447.20","TotalPayments":"42215.05","BalanceMoney":null,"InHosDay":350,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D17339","HID":"47090","CaseID":"D17339","SubID":"5","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"9","DoctorInCharge":"刘杨","NurseInCharge":null,"Diagnosis":"腰椎管狭窄","Name":"雷花英","NamePhonetic":"LEI HUA YING","Nation":"汉族","Sex":"女","BirthDay":"1940/2/3 0:00:00","Age":"76","EnterDate":"2015/4/20 9:53:53","ExitDate":null,"MedicalInsuranceID":"10153206600S","IdentityType":"一般人员","Company":"北京市敬业电工集团","Phone":null,"Address":"北京市海淀区厂洼街乙5号","ZipCode":"100089","LinkManName":"张晓燕","LinkManRelation":"子女","LinkManPhone":"13671188271","IdentityNumber":"110102194002031129","Payment":"医疗保险","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"83000.00","TotalCost":"135842.04","TotalPayments":"78289.44","BalanceMoney":null,"InHosDay":343,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"C734","HID":"27736","CaseID":"C734","SubID":"5","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"10","DoctorInCharge":"田丰富","NurseInCharge":null,"Diagnosis":"膝骨关节病","Name":"焦小兰","NamePhonetic":"JIAO XIAO LAN","Nation":"汉族","Sex":"女","BirthDay":"1950/9/8 0:00:00","Age":"66","EnterDate":"2015/4/17 10:51:58","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"中办老干部局","Phone":null,"Address":"北京市海淀区万寿路乙5号501室","ZipCode":"100036","LinkManName":"常金玉","LinkManRelation":"子女","LinkManPhone":"13661039185","IdentityNumber":"未带","Payment":"公费医疗","CurrentState":"一般","CareLevel":"一级护理","PrePayments":"59000.00","TotalCost":"78628.15","TotalPayments":"47014.75","BalanceMoney":null,"InHosDay":346,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D255283","HID":"82233","CaseID":"D255283","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"12","DoctorInCharge":"刘洋","NurseInCharge":null,"Diagnosis":"膝骨关节炎","Name":"蒋道军","NamePhonetic":"JIANG DAO JUN","Nation":"汉族","Sex":"男","BirthDay":"1950/4/11 0:00:00","Age":"66","EnterDate":"2015/4/20 9:38:19","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"无","Phone":null,"Address":"安徽省淮南市谢家集区望峰岗镇二道河村十一","ZipCode":"230000","LinkManName":"蒋保闯","LinkManRelation":"子女","LinkManPhone":"13811614216","IdentityNumber":"340404195004116114","Payment":"异地保险","CurrentState":"一般","CareLevel":"一级护理","PrePayments":"20000.00","TotalCost":"23972.92","TotalPayments":"16785.67","BalanceMoney":null,"InHosDay":343,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D255441","HID":"82005","CaseID":"D255441","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"13","DoctorInCharge":"刘杨","NurseInCharge":null,"Diagnosis":"腰痛","Name":"刘爱生","NamePhonetic":"LIU AI SHENG","Nation":"汉族","Sex":"女","BirthDay":"1949/9/9 0:00:00","Age":"67","EnterDate":"2015/4/10 9:37:13","ExitDate":null,"MedicalInsuranceID":"10285961900S","IdentityType":"一般人员","Company":"中建贸易公司","Phone":null,"Address":"北京市甘家口建设部9号院西803","ZipCode":"100000","LinkManName":"刘月","LinkManRelation":"子女","LinkManPhone":"15000101676","IdentityNumber":"110108194909095428","Payment":"医疗保险","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"53000.00","TotalCost":"92418.14","TotalPayments":"55143.14","BalanceMoney":null,"InHosDay":353,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D255402","HID":"81991","CaseID":"D255402","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"16","DoctorInCharge":"刘洋","NurseInCharge":null,"Diagnosis":"腰椎间盘突出","Name":"李兰欣","NamePhonetic":"LI LAN XIN","Nation":"汉族","Sex":"女","BirthDay":"1954/12/2 0:00:00","Age":"62","EnterDate":"2015/4/9 10:44:18","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"河北定州市通力机械厂","Phone":null,"Address":"河北省定州市中山丽都小区4号楼三单元201","ZipCode":"073000","LinkManName":"郭申","LinkManRelation":"子女","LinkManPhone":"13582825818","IdentityNumber":"132439195412020026","Payment":"异地保险","CurrentState":"病重","CareLevel":"一级护理","PrePayments":"80000.00","TotalCost":"114473.65","TotalPayments":"72591.25","BalanceMoney":null,"InHosDay":354,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D254871","HID":"81896","CaseID":"D254871","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"21","DoctorInCharge":"田丰富","NurseInCharge":null,"Diagnosis":"腰椎间盘突出","Name":"张淑芳","NamePhonetic":"ZHANG SHU FANG","Nation":"汉族","Sex":"女","BirthDay":"1941/7/29 0:00:00","Age":"75","EnterDate":"2015/4/6 16:28:27","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"吉林辽源市供销社","Phone":null,"Address":"吉林辽源市龙山区新兴街一委三十三组","ZipCode":"136200","LinkManName":"倪晓红","LinkManRelation":"(岳)父母","LinkManPhone":"13943799443","IdentityNumber":"220402194107295026","Payment":"异地保险","CurrentState":"一般","CareLevel":"一级护理","PrePayments":"110000.00","TotalCost":"162029.91","TotalPayments":"103429.41","BalanceMoney":null,"InHosDay":357,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D251485","HID":"81315","CaseID":"D251485","SubID":"2","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"22","DoctorInCharge":"刘明","NurseInCharge":null,"Diagnosis":"足部感染","Name":"孙研","NamePhonetic":"SUN YAN","Nation":"汉族","Sex":"女","BirthDay":"1978/12/27 0:00:00","Age":"38","EnterDate":"2015/4/6 18:57:26","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"无","Phone":null,"Address":"吉林省白城市洮北区金辉南街43号楼1单元602","ZipCode":"137000","LinkManName":"孙学晟","LinkManRelation":"夫妻","LinkManPhone":"13843665151","IdentityNumber":"222301197812270629","Payment":"异地保险","CurrentState":"一般","CareLevel":"一级护理","PrePayments":"20000.00","TotalCost":"17536.66","TotalPayments":"16425.76","BalanceMoney":null,"InHosDay":357,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D254370","HID":"81745","CaseID":"D254370","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"23","DoctorInCharge":"刘杨","NurseInCharge":null,"Diagnosis":"腰痛","Name":"杨瑞珍","NamePhonetic":"YANG RUI ZHEN","Nation":"汉族","Sex":"女","BirthDay":"1932/4/4 0:00:00","Age":"84","EnterDate":"2015/3/28 16:27:17","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"联通北京分公司","Phone":null,"Address":"北京宣武区信建里5号楼3门103","ZipCode":"100038","LinkManName":"吕媛","LinkManRelation":"子女","LinkManPhone":"15611027966","IdentityNumber":"110104193204041243","Payment":"公费医疗","CurrentState":"一般","CareLevel":"二级护理","PrePayments":null,"TotalCost":"1007496.86","TotalPayments":"538703.36","BalanceMoney":null,"InHosDay":366,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D256484","HID":"82222","CaseID":"D256484","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"24","DoctorInCharge":"刘杨","NurseInCharge":null,"Diagnosis":"腰痛","Name":"范开连","NamePhonetic":"FAN KAI LIAN","Nation":"汉族","Sex":"女","BirthDay":"1960/2/9 0:00:00","Age":"56","EnterDate":"2015/4/20 9:06:06","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"无","Phone":null,"Address":"河北省张家口市张北县张北镇黄土湾行政村","ZipCode":"075000","LinkManName":"范凯琴","LinkManRelation":"亲属","LinkManPhone":"15028320786","IdentityNumber":null,"Payment":"异地保险","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"90000.00","TotalCost":"99589.83","TotalPayments":"61051.68","BalanceMoney":null,"InHosDay":343,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D255479","HID":"82065","CaseID":"D255479","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"25","DoctorInCharge":"田丰富","NurseInCharge":null,"Diagnosis":"髋关节痛","Name":"丁桂英","NamePhonetic":"DING GUI YING","Nation":"汉族","Sex":"女","BirthDay":"1966/5/20 0:00:00","Age":"50","EnterDate":"2015/4/13 8:50:41","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"无","Phone":null,"Address":"河南省鹿邑县张店乡东谢楼行政村东谢楼52号","ZipCode":"477284","LinkManName":"王超军","LinkManRelation":"夫妻","LinkManPhone":"15939450635","IdentityNumber":"41272519660520346X","Payment":"异地保险","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"80000.00","TotalCost":"137036.70","TotalPayments":"76983.00","BalanceMoney":null,"InHosDay":351,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D256584","HID":"82294","CaseID":"D256584","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"26","DoctorInCharge":"田丰富","NurseInCharge":null,"Diagnosis":"足部畸形","Name":"牛兴玺","NamePhonetic":"NIU XING XI","Nation":"汉族","Sex":"女","BirthDay":"1970/9/16 0:00:00","Age":"46","EnterDate":"2015/4/22 11:03:14","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"无","Phone":null,"Address":"河南驻马店泌阳县太山乡荣庄村关帝","ZipCode":"463000","LinkManName":"牛兴军","LinkManRelation":"亲属","LinkManPhone":"13520212630","IdentityNumber":"412822197009162623","Payment":"异地保险","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"10000.00","TotalCost":"1321.54","TotalPayments":"1321.54","BalanceMoney":null,"InHosDay":341,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D255908","HID":"82272","CaseID":"D255908","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"27","DoctorInCharge":"刘洋","NurseInCharge":null,"Diagnosis":"腰椎骨折","Name":"杨善宝","NamePhonetic":"YANG SHAN BAO","Nation":"汉族","Sex":"男","BirthDay":"1988/12/16 0:00:00","Age":"28","EnterDate":"2015/4/21 15:55:36","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"无","Phone":null,"Address":"江苏省灌云县同兴镇振兴居委会老后街20号","ZipCode":"222206","LinkManName":"顾芳","LinkManRelation":"夫妻","LinkManPhone":"18261358596","IdentityNumber":"320723198812162851","Payment":"异地保险","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"60000.00","TotalCost":"85589.30","TotalPayments":"48940.10","BalanceMoney":null,"InHosDay":342,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D204677","HID":"71456","CaseID":"D204677","SubID":"2","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"28","DoctorInCharge":"刘明","NurseInCharge":null,"Diagnosis":"腰椎间盘突出","Name":"赵容生","NamePhonetic":"ZHAO RONG SHENG","Nation":"汉族","Sex":"男","BirthDay":"1988/9/11 0:00:00","Age":"28","EnterDate":"2015/4/27 11:39:26","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"山西襄垣县发改局","Phone":null,"Address":"山西襄垣县东北阳村12号","ZipCode":"046200","LinkManName":"郝丽","LinkManRelation":"亲属","LinkManPhone":"13620653681","IdentityNumber":"140402198809112413","Payment":"异地保险","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"10000.00","TotalCost":"828.79","TotalPayments":"828.79","BalanceMoney":null,"InHosDay":336,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D152668","HID":"60316","CaseID":"D152668","SubID":"2","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"29","DoctorInCharge":"刘明","NurseInCharge":null,"Diagnosis":"腰椎间盘突出","Name":"李国文","NamePhonetic":"LI GUO WEN","Nation":"汉族","Sex":"男","BirthDay":"1976/8/19 0:00:00","Age":"40","EnterDate":"2015/4/8 16:47:34","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"无","Phone":null,"Address":"山西繁峙县东山乡联兴村5排52号","ZipCode":"034302","LinkManName":"段兰弟","LinkManRelation":"亲属","LinkManPhone":"13994078802","IdentityNumber":"142226197608195272","Payment":"异地保险","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"30000.00","TotalCost":"12065.38","TotalPayments":"12065.38","BalanceMoney":null,"InHosDay":355,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"D256843","HID":"82305","CaseID":"D256843","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"30","DoctorInCharge":"刘明","NurseInCharge":null,"Diagnosis":"左手外伤","Name":"刘长龙","NamePhonetic":"LIU CHANG LONG","Nation":"汉族","Sex":"男","BirthDay":"1964/11/8 0:00:00","Age":"52","EnterDate":"2015/4/22 19:11:31","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"一般人员","Company":"无","Phone":null,"Address":"河北省廊坊市固安县城关镇大留村","ZipCode":"065500","LinkManName":"刘杨","LinkManRelation":"子女","LinkManPhone":"15230670118","IdentityNumber":"急诊住院","Payment":"异地保险","CurrentState":"一般","CareLevel":"二级护理","PrePayments":"25000.00","TotalCost":"12405.86","TotalPayments":"12405.86","BalanceMoney":null,"InHosDay":341,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"B23242","HID":"81695","CaseID":"B23242","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"33","DoctorInCharge":"田丰富","NurseInCharge":null,"Diagnosis":"膝关节功能紊乱","Name":"刘佳音","NamePhonetic":"LIU JIA YIN","Nation":"汉族","Sex":"男","BirthDay":"1986/10/5 0:00:00","Age":"30","EnterDate":"2015/3/25 10:35:21","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"战士","Company":"中央警卫团五大队","Phone":"03354099844","Address":"北戴河海滨驻军1号","ZipCode":"066100","LinkManName":"杨志","LinkManRelation":"同事","LinkManPhone":"03354099844","IdentityNumber":null,"Payment":"军队医改","CurrentState":"一般","CareLevel":"二级护理","PrePayments":null,"TotalCost":"21237.00","TotalPayments":"21237.00","BalanceMoney":null,"InHosDay":369,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"B23038","HID":"81850","CaseID":"B23038","SubID":"2","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"35","DoctorInCharge":"田丰富","NurseInCharge":null,"Diagnosis":"皮肤开放性外伤","Name":"杨帆","NamePhonetic":"YANG FAN","Nation":"汉族","Sex":"男","BirthDay":"1995/3/3 0:00:00","Age":"21","EnterDate":"2015/4/24 15:11:42","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"战士","Company":"中央警卫团一大队四中队","Phone":"83085041","Address":"北京市1717信箱4号","ZipCode":"100017","LinkManName":"刘小苏","LinkManRelation":"同事","LinkManPhone":"83085041","IdentityNumber":null,"Payment":"军队医改","CurrentState":"一般","CareLevel":"一级护理","PrePayments":null,"TotalCost":"2818.77","TotalPayments":"2818.77","BalanceMoney":null,"InHosDay":339,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"B23247","HID":"81966","CaseID":"B23247","SubID":"2","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"36","DoctorInCharge":"田丰富","NurseInCharge":null,"Diagnosis":"右中足损伤","Name":"李易峰","NamePhonetic":"LI YI FENG","Nation":"汉族","Sex":"男","BirthDay":"1997/4/6 0:00:00","Age":"19","EnterDate":"2015/4/27 10:17:21","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"战士","Company":"警卫局管理中队","Phone":"63096552","Address":"北京市1713信箱1号","ZipCode":"100017","LinkManName":"孙圣健","LinkManRelation":"同事","LinkManPhone":"63096552","IdentityNumber":null,"Payment":"军队医改","CurrentState":"一般","CareLevel":"二级护理","PrePayments":null,"TotalCost":"888.60","TotalPayments":"888.60","BalanceMoney":null,"InHosDay":336,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"B23292","HID":"82088","CaseID":"B23292","SubID":"1","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"37","DoctorInCharge":"田丰富","NurseInCharge":null,"Diagnosis":"软组织感染","Name":"田豪","NamePhonetic":"TIAN HAO","Nation":"汉族","Sex":"男","BirthDay":"1997/5/16 0:00:00","Age":"19","EnterDate":"2015/4/13 15:42:13","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"战士","Company":"中央警卫团一大队四中队","Phone":"83085041","Address":"北京市1717信箱4号","ZipCode":"100017","LinkManName":"高胜","LinkManRelation":"同事","LinkManPhone":"83085041","IdentityNumber":null,"Payment":"军队医改","CurrentState":"一般","CareLevel":"二级护理","PrePayments":null,"TotalCost":"3488.87","TotalPayments":"3488.87","BalanceMoney":null,"InHosDay":350,"IsWaiting":0,"DiagnsisHistoryList":null},{"ID":"B22766","HID":"80053","CaseID":"B22766","SubID":"2","ClinicID":null,"DeptCode":"310303","DeptName":"骨科病区","DischargeDeptName":null,"WardName":"骨科病区护士站","BedNo":"38","DoctorInCharge":"刘明","NurseInCharge":null,"Diagnosis":"腰椎间盘突出","Name":"周子乔","NamePhonetic":"ZHOU ZI QIAO","Nation":"汉族","Sex":"男","BirthDay":"1994/3/6 0:00:00","Age":"22","EnterDate":"2015/4/1 15:46:23","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"战士","Company":"中央警卫团五大队一中队","Phone":"0335-4099842","Address":"河北省北戴河海滨驻军1号","ZipCode":"066000","LinkManName":"杨志","LinkManRelation":"同事","LinkManPhone":"0335-4099855","IdentityNumber":null,"Payment":"军队医改","CurrentState":"一般","CareLevel":"二级护理","PrePayments":null,"TotalCost":"13866.97","TotalPayments":"12921.97","BalanceMoney":null,"InHosDay":362,"IsWaiting":0,"DiagnsisHistoryList":null}]
     */

    private int Status;
    /**
     * ID : D257318
     * HID : 82392
     * CaseID : D257318
     * SubID : 1
     * ClinicID : null
     * DeptCode : 310303
     * DeptName : 骨科病区
     * DischargeDeptName : null
     * WardName : 骨科病区护士站
     * BedNo : 2
     * DoctorInCharge : 刘洋
     * NurseInCharge : null
     * Diagnosis : 腰椎骨折
     * Name : 黄淑英
     * NamePhonetic : HUANG SHU YING
     * Nation : 汉族
     * Sex : 女
     * BirthDay : 1932/4/14 0:00:00
     * Age : 84
     * EnterDate : 2015/4/28 10:42:47
     * ExitDate : null
     * MedicalInsuranceID : 10063643901S
     * IdentityType : 一般人员
     * Company : 北京象牙雕刻厂有限责任公司
     * Phone : null
     * Address : 北京市丰台区嘉园二里4号楼4门102
     * ZipCode : 100062
     * LinkManName : 郑志惠
     * LinkManRelation : 子女
     * LinkManPhone : 13120011961
     * IdentityNumber : 11010319320414182X
     * Payment : 医疗保险
     * CurrentState : 一般
     * CareLevel : 一级护理
     * PrePayments : 23000.00
     * TotalCost : 76810.28
     * TotalPayments : 39535.28
     * BalanceMoney : null
     * InHosDay : 335
     * IsWaiting : 0
     * DiagnsisHistoryList : null
     */

    private List<ValuesBean> Values;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public List<ValuesBean> getValues() {
        return Values;
    }

    public void setValues(List<ValuesBean> Values) {
        this.Values = Values;
    }


    public static class ValuesBean implements Serializable {

        public ValuesBean(String ID, String subID, String name, String enterDate, String doctorInCharge, String HID, String age) {
            this.ID = ID;
            SubID = subID;
            Name = name;
            EnterDate = enterDate;
            DoctorInCharge = doctorInCharge;
            this.HID = HID;
            Age = age;
        }

        private String ID;
        private String HID;
        private String CaseID;
        private String SubID;
        private Object ClinicID;
        private String DeptCode;
        private String DeptName;
        private Object DischargeDeptName;
        private String WardName;
        private String BedNo;
        private String DoctorInCharge;
        private Object NurseInCharge;
        private String Diagnosis;
        private String Name;
        private String NamePhonetic;
        private String Nation;
        private String Sex;
        private String BirthDay;
        private String Age;
        private String EnterDate;
        private Object ExitDate;
        private String MedicalInsuranceID;
        private String IdentityType;
        private String Company;
        private Object Phone;
        private String Address;
        private String ZipCode;
        private String LinkManName;
        private String LinkManRelation;
        private String LinkManPhone;
        private String IdentityNumber;
        private String Payment;
        private String CurrentState;
        private String CareLevel;
        private String PrePayments;
        private String TotalCost;
        private String TotalPayments;
        private Object BalanceMoney;
        private int InHosDay;
        private int IsWaiting;
        private Object DiagnsisHistoryList;
        private int IsCollection;

        public int getIsCollection() {
            return IsCollection;
        }

        public void setIsCollection(int isCollection) {
            IsCollection = isCollection;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getHID() {
            return HID;
        }

        public void setHID(String HID) {
            this.HID = HID;
        }

        public String getCaseID() {
            return CaseID;
        }

        public void setCaseID(String CaseID) {
            this.CaseID = CaseID;
        }

        public String getSubID() {
            return SubID;
        }

        public void setSubID(String SubID) {
            this.SubID = SubID;
        }

        public Object getClinicID() {
            return ClinicID;
        }

        public void setClinicID(Object ClinicID) {
            this.ClinicID = ClinicID;
        }

        public String getDeptCode() {
            return DeptCode;
        }

        public void setDeptCode(String DeptCode) {
            this.DeptCode = DeptCode;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public Object getDischargeDeptName() {
            return DischargeDeptName;
        }

        public void setDischargeDeptName(Object DischargeDeptName) {
            this.DischargeDeptName = DischargeDeptName;
        }

        public String getWardName() {
            return WardName;
        }

        public void setWardName(String WardName) {
            this.WardName = WardName;
        }

        public String getBedNo() {
            return BedNo;
        }

        public void setBedNo(String BedNo) {
            this.BedNo = BedNo;
        }

        public String getDoctorInCharge() {
            return DoctorInCharge;
        }

        public void setDoctorInCharge(String DoctorInCharge) {
            this.DoctorInCharge = DoctorInCharge;
        }

        public Object getNurseInCharge() {
            return NurseInCharge;
        }

        public void setNurseInCharge(Object NurseInCharge) {
            this.NurseInCharge = NurseInCharge;
        }

        public String getDiagnosis() {
            return Diagnosis;
        }

        public void setDiagnosis(String Diagnosis) {
            this.Diagnosis = Diagnosis;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getNamePhonetic() {
            return NamePhonetic;
        }

        public void setNamePhonetic(String NamePhonetic) {
            this.NamePhonetic = NamePhonetic;
        }

        public String getNation() {
            return Nation;
        }

        public void setNation(String Nation) {
            this.Nation = Nation;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getBirthDay() {
            return BirthDay;
        }

        public void setBirthDay(String BirthDay) {
            this.BirthDay = BirthDay;
        }

        public String getAge() {
            return Age;
        }

        public void setAge(String Age) {
            this.Age = Age;
        }

        public String getEnterDate() {
            return EnterDate;
        }

        public void setEnterDate(String EnterDate) {
            this.EnterDate = EnterDate;
        }

        public Object getExitDate() {
            return ExitDate;
        }

        public void setExitDate(Object ExitDate) {
            this.ExitDate = ExitDate;
        }

        public String getMedicalInsuranceID() {
            return MedicalInsuranceID;
        }

        public void setMedicalInsuranceID(String MedicalInsuranceID) {
            this.MedicalInsuranceID = MedicalInsuranceID;
        }

        public String getIdentityType() {
            return IdentityType;
        }

        public void setIdentityType(String IdentityType) {
            this.IdentityType = IdentityType;
        }

        public String getCompany() {
            return Company;
        }

        public void setCompany(String Company) {
            this.Company = Company;
        }

        public Object getPhone() {
            return Phone;
        }

        public void setPhone(Object Phone) {
            this.Phone = Phone;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getZipCode() {
            return ZipCode;
        }

        public void setZipCode(String ZipCode) {
            this.ZipCode = ZipCode;
        }

        public String getLinkManName() {
            return LinkManName;
        }

        public void setLinkManName(String LinkManName) {
            this.LinkManName = LinkManName;
        }

        public String getLinkManRelation() {
            return LinkManRelation;
        }

        public void setLinkManRelation(String LinkManRelation) {
            this.LinkManRelation = LinkManRelation;
        }

        public String getLinkManPhone() {
            return LinkManPhone;
        }

        public void setLinkManPhone(String LinkManPhone) {
            this.LinkManPhone = LinkManPhone;
        }

        public String getIdentityNumber() {
            return IdentityNumber;
        }

        public void setIdentityNumber(String IdentityNumber) {
            this.IdentityNumber = IdentityNumber;
        }

        public String getPayment() {
            return Payment;
        }

        public void setPayment(String Payment) {
            this.Payment = Payment;
        }

        public String getCurrentState() {
            return CurrentState;
        }

        public void setCurrentState(String CurrentState) {
            this.CurrentState = CurrentState;
        }

        public String getCareLevel() {
            return CareLevel;
        }

        public void setCareLevel(String CareLevel) {
            this.CareLevel = CareLevel;
        }

        public String getPrePayments() {
            return PrePayments;
        }

        public void setPrePayments(String PrePayments) {
            this.PrePayments = PrePayments;
        }

        public String getTotalCost() {
            return TotalCost;
        }

        public void setTotalCost(String TotalCost) {
            this.TotalCost = TotalCost;
        }

        public String getTotalPayments() {
            return TotalPayments;
        }

        public void setTotalPayments(String TotalPayments) {
            this.TotalPayments = TotalPayments;
        }

        public Object getBalanceMoney() {
            return BalanceMoney;
        }

        public void setBalanceMoney(Object BalanceMoney) {
            this.BalanceMoney = BalanceMoney;
        }

        public int getInHosDay() {
            return InHosDay;
        }

        public void setInHosDay(int InHosDay) {
            this.InHosDay = InHosDay;
        }

        public int getIsWaiting() {
            return IsWaiting;
        }

        public void setIsWaiting(int IsWaiting) {
            this.IsWaiting = IsWaiting;
        }

        public Object getDiagnsisHistoryList() {
            return DiagnsisHistoryList;
        }

        public void setDiagnsisHistoryList(Object DiagnsisHistoryList) {
            this.DiagnsisHistoryList = DiagnsisHistoryList;
        }
    }
}
