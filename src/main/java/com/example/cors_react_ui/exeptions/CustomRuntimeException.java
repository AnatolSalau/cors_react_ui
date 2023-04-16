package com.example.cors_react_ui.exeptions;

public class CustomRuntimeException extends RuntimeException{
      private int statusCode;
      public CustomRuntimeException(int statusCode, String message) {
            super(message) ;
            this.statusCode = statusCode;
      }

      public int getStatusCode() {
            return statusCode;
      }

      public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
      }
}
