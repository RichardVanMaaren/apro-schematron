package com.apro.schematronvalidation;

import com.helger.schematron.svrl.jaxb.FailedAssert;
import com.helger.schematron.svrl.jaxb.SchematronOutputType;
import com.helger.schematron.svrl.jaxb.Text;

import java.io.File;
import java.util.List;
import com.helger.schematron.ISchematronResource;

import javax.annotation.Nonnull;
import javax.xml.transform.stream.StreamSource;
import com.helger.schematron.sch.SchematronResourceSCH;



public class AproTesterviaphschematron {
     private String theFile;
      AproTesterviaphschematron(String theFile){


         this.setTheFile(theFile);
      }


    public String getTheFile() {
        return theFile;
    }

    public void setTheFile(String theFile) {
        this.theFile = theFile;
    }




     private void performAgainst(String schema, File file) throws Exception {
        System.out.println( "");
        System.out.println("Schematron : " + schema);

        File theSchema = new File(schema);


        //   System.out.println( validateXMLViaXSLTSchematron(theSchema, file));
        System.out.println( "Valid : "+ this.validateXMLViaXSLTSchematronBoolean(theSchema, file));

        SchematronOutputType iets = validateXMLViaXSLTSchematron(theSchema, file);

        List<Object> failedAsserts = iets.getActivePatternAndFiredRuleAndFailedAssert();
        for (Object object : failedAsserts) {
            if (object instanceof FailedAssert) {
                FailedAssert failedAssert = (FailedAssert) object;
                //  System.out.println("Rule : "+ failedAssert.getDiagnosticReferenceOrPropertyReferenceOrText());

                System.out.println("ID   : "+ failedAssert.getId());
                System.out.println("Type : "+ failedAssert.getFlag());
                System.out.println("Tag  : "+ failedAssert.getLocation());
                List<Object> a = failedAssert.getDiagnosticReferenceOrPropertyReferenceOrText();

                for (Object object2 : a) {
                    if (object2 instanceof Text) {
                        Text b = (Text) object2;

                        System.out.println("Content "+ b.getContent());

                    }

                }


            }
        }


    }
    public static SchematronOutputType validateXMLViaXSLTSchematron (@Nonnull final File aSchematronFile, @Nonnull final File aXMLFile) throws Exception
    {
        final ISchematronResource aResSCH = SchematronResourceSCH.fromFile (aSchematronFile);
        if (!aResSCH.isValidSchematron ())
            throw new IllegalArgumentException ("Invalid Schematron!");
        SchematronOutputType iets= aResSCH.applySchematronValidationToSVRL(new StreamSource (aXMLFile));
        // return aResSCH.getSchematronValidity (new StreamSource(aXMLFile)).isValid ();
        return iets;
    }

    public static boolean validateXMLViaXSLTSchematronBoolean (@Nonnull final File aSchematronFile, @Nonnull final File aXMLFile) throws Exception
    {
        final ISchematronResource aResSCH = SchematronResourceSCH.fromFile (aSchematronFile);
        if (!aResSCH.isValidSchematron ())
            throw new IllegalArgumentException ("Invalid Schematron!");
        //  SchematronOutputType iets= aResSCH.applySchematronValidationToSVRL(new StreamSource (aXMLFile));
        return aResSCH.getSchematronValidity (new StreamSource(aXMLFile)).isValid ();

    }
    public void performPintReview() throws Exception {

        System.out.println("Performing PINT");
        System.out.println("File   : " + this.getTheFile());
        performAgainst("c:/Oxalis/schematron/test/PINT-jurisdiction-aligned-rules.sch",new File(this.getTheFile()));
        performAgainst("c:/Oxalis/schematron/test/PINT-UBL-validation-preprocessed.sch",new File(this.getTheFile()));
        performAgainst("c:/Oxalis/schematron/test/PINT-EN16931-aligned-rules.sch",new File(this.getTheFile()));

    }

    public  void performPeppolBis() throws Exception {

        System.out.println("Performing Peppol Bis");
        System.out.println("FIle   : " + this.getTheFile());
        performAgainst("c:/Oxalis/schematron/test/CEN-EN16931-UBL.sch",new File(this.getTheFile()));
        performAgainst("c:/Oxalis/schematron/test/PEPPOL-EN16931-UBL.sch",new File(this.getTheFile()));


    }

    public  void performANZReview() throws Exception {

        System.out.println("Performing ANZ");
        System.out.println("FIle   : " + this.getTheFile());
        performAgainst("c:/Oxalis/schematron/test/AUNZ-PEPPOL-SB-validation.sch",new File(this.getTheFile()));
        performAgainst("c:/Oxalis/schematron/test/AUNZ-PEPPOL-validation.sch",new File(this.getTheFile()));
        performAgainst("c:/Oxalis/schematron/test/AUNZ-UBL-validation.sch",new File(this.getTheFile()));

    }
}
