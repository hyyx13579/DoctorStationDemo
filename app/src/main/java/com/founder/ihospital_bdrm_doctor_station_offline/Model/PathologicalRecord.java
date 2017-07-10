package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hyyx on 16/8/30.
 */
public class PathologicalRecord {


    /**
     * Status : 1
     * Values : [{"CheckMethod":null,"Conclusion":"标本名称    块数  炎症  肠化  萎缩  颈增  非典  其他\\@\\-----------------------------------------------------------------------\\@\\胃窦        1     ++    -     -     -     -     \\@\\-----------------------------------------------------------------------\\@\\免疫组化染色结果：HP（-）","ReportDoctor":"马英腾","AuditingDoctor":"刘芳芳","ReportDate":"2014-06-19 00:00:00","ItemName":"病理"},{"CheckMethod":null,"Conclusion":"标本名称    块数  炎症  肠化  萎缩  颈增  非典  其他\\@\\-----------------------------------------------------------------------\\@\\胃窦        1     +++   -     -     -     -     灶状淋巴细胞聚集\\@\\-----------------------------------------------------------------------\\@\\免疫组化染色结果：胃窦HP（-）","ReportDoctor":"郑娇","AuditingDoctor":"高松源","ReportDate":"2014-08-12 00:00:00","ItemName":"病理"}]
     */

    private int Status;
    /**
     * CheckMethod : null
     * Conclusion : 标本名称    块数  炎症  肠化  萎缩  颈增  非典  其他\@\-----------------------------------------------------------------------\@\胃窦        1     ++    -     -     -     -     \@\-----------------------------------------------------------------------\@\免疫组化染色结果：HP（-）
     * ReportDoctor : 马英腾
     * AuditingDoctor : 刘芳芳
     * ReportDate : 2014-06-19 00:00:00
     * ItemName : 病理
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
        private Object CheckMethod;
        private String Conclusion;
        private String ReportDoctor;
        private String AuditingDoctor;
        private String ReportDate;
        private String ItemName;

        public Object getCheckMethod() {
            return CheckMethod;
        }

        public void setCheckMethod(Object CheckMethod) {
            this.CheckMethod = CheckMethod;
        }

        public String getConclusion() {
            return Conclusion;
        }

        public void setConclusion(String Conclusion) {
            this.Conclusion = Conclusion;
        }

        public String getReportDoctor() {
            return ReportDoctor;
        }

        public void setReportDoctor(String ReportDoctor) {
            this.ReportDoctor = ReportDoctor;
        }

        public String getAuditingDoctor() {
            return AuditingDoctor;
        }

        public void setAuditingDoctor(String AuditingDoctor) {
            this.AuditingDoctor = AuditingDoctor;
        }

        public String getReportDate() {
            return ReportDate;
        }

        public void setReportDate(String ReportDate) {
            this.ReportDate = ReportDate;
        }

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String ItemName) {
            this.ItemName = ItemName;
        }
    }
}
