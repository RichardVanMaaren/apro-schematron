package com.apro.schematronvalidation;


import com.helger.commons.error.list.ErrorList;
import com.helger.phive.api.execute.ValidationExecutionManager;
import com.helger.phive.api.executorset.IValidationExecutorSet;
import com.helger.phive.api.executorset.ValidationExecutorSetRegistry;
import com.helger.phive.api.result.ValidationResultList;
import com.helger.phive.engine.source.IValidationSourceXML;
import com.helger.phive.engine.source.ValidationSourceXML;
import com.helger.phive.peppol.PeppolValidation;
import com.helger.phive.peppol.PeppolValidation3_14_0;

import com.helger.schematron.svrl.SVRLResourceError;
import com.helger.xml.serialize.read.DOMReader;
import org.w3c.dom.Element;

import java.io.File;
import java.util.Locale;

public class AproTesterviaphPhive {
    private String theFile;
    AproTesterviaphPhive(String theFile){
        this.setTheFile(theFile);
    }


    public void performPhiveValidation(){
        // Resolve the VES ID
        // Resolve the VES ID
        final ValidationExecutorSetRegistry <IValidationSourceXML> aVESRegistry = new ValidationExecutorSetRegistry<>();
        PeppolValidation.initStandard (aVESRegistry);
        final IValidationExecutorSet<IValidationSourceXML> aVES = aVESRegistry.getOfID(PeppolValidation3_14_0.VID_OPENPEPPOL_INVOICE_UBL_V3);

        final Element aPayloadElement = DOMReader.readXMLDOM (new File (this.getTheFile()))
                .getDocumentElement ();

        if (aVES != null) {

        }


            final ValidationResultList aValidationResult = ValidationExecutionManager.executeValidation(aVES,   ValidationSourceXML.create (null, aPayloadElement));
            if (aValidationResult.containsAtLeastOneError()) {
                System.out.println("In error");

                ErrorList failedAsserts = aValidationResult.getAllErrors();
                for (Object object : failedAsserts) {
                    if (object instanceof SVRLResourceError) {
                        SVRLResourceError failedAssert = (SVRLResourceError) object;


                        System.out.println(failedAssert.getErrorLevel());
                        System.out.println(failedAssert.getErrorFieldName());
                        System.out.println(failedAssert.getErrorID());
                        System.out.println(failedAssert.getErrorText(Locale.ENGLISH));
                    }
                }


            } else {
                System.out.println("No issues");
            }
        }


    public String getTheFile() {
        return theFile;
    }

    public void setTheFile(String theFile) {
        this.theFile = theFile;
    }
}