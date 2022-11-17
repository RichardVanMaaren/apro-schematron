package com.apro.schematronvalidation;


public class Main {
   // static final String theFilePint="c:/Oxalis/schematron/test/EU2EUICGS1BELUsimpleinvoiceAPROtoBaswareNoHeader.xml";
    static final String theFilePint="c:/Oxalis/schematron/test/TestFile_001__BISv3_Invoice.xml";

    public static void main(String[] args) throws Exception {
        AproTesterviaphschematron tester = new AproTesterviaphschematron(theFilePint);


        tester.performPintReview();

    //    tester.performPeppolBis();

      //  tester.performANZReview();
        //In case of using Phive as validatrion mechanism
        AproTesterviaphPhive testerPhive = new AproTesterviaphPhive(theFilePint);
        testerPhive.performPhiveValidation();

    }






}


