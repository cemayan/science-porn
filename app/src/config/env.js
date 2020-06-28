const dev = {
  s3: {
    BUCKET: "YOUR_DEV_S3_UPLOADS_BUCKET_NAME",
  },
};

const prod = {
  s3: {
    BUCKET: "YOUR_PROD_S3_UPLOADS_BUCKET_NAME",
  },
};

const config = process.env.REACT_APP_STAGE === "production" ? prod : dev;

export default {
  // Add common config values here
  MAX_ATTACHMENT_SIZE: 5000000,
  ...config,
};
