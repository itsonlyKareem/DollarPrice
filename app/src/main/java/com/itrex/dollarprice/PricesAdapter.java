package com.itrex.dollarprice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PricesAdapter extends RecyclerView.Adapter<PricesAdapter.ViewHolder> {
    private List<PricesModel> list;
    int Images[] = {R.drawable.ic_aaib,R.drawable.adib,R.drawable.bam,R.drawable.bar,R.drawable.bdc,
    R.drawable.blum,R.drawable.ic_alexbank,R.drawable.cae,R.drawable.ic_cbe,R.drawable.ic_cib,
    R.drawable.ic_egbank,R.drawable.fib,R.drawable.had,R.drawable.ic_hsbc,
    R.drawable.ic_nbe,R.drawable.qnb,R.drawable.scb};

    public PricesAdapter(List<PricesModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.prices_row, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myText1.setText(list.get(position).getBuy());
        holder.myText2.setText(list.get(position).getSell());
        holder.myText3.setText(list.get(position).getName());
        holder.myImage.setImageResource(Images[position]);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (holder.myText3.getText().equals("مصرف أبو ظبي الإسلامي")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19951"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();

                } else if (holder.myText3.getText().equals("بنك مصر")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19888"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                } else if (holder.myText3.getText().equals("بنك البركة")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19373"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                } else if (holder.myText3.getText().equals("بنك القاهرة")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "16990"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                } else if (holder.myText3.getText().equals("بنك بلوم")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19233"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                } else if (holder.myText3.getText().equals("بنك الاسكندرية")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19033"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                } else if (holder.myText3.getText().equals("كريدي أجريكول")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19191"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                } else if (holder.myText3.getText().equals("البنك المركزي المصري")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0 2 27702770"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                } else if (holder.myText3.getText().equals("البنك المصري الخليجي")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19342"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                } else if (holder.myText3.getText().equals("بنك فيصل الإسلامي")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19851"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                } else if (holder.myText3.getText().equals("بنك التعمير و الإسكان")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19995"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                } else if (holder.myText3.getText().equals("البنك الاهلي المصري")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19623"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                } else if (holder.myText3.getText().equals("بنك قطر الوطني")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19753"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                } else if (holder.myText3.getText().equals("بنك قناة السويس")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19093"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                } else if (holder.myText3.getText().toString().contains("AAIB")) {
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("تأكيد")
                            .setMessage("هل تريد الإتصال بهذا المصرف؟")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19555"));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no,null)
                            .show();
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return Images.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView myText1, myText2, myText3;
        ImageView myImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.buyRate);
            myText2 = itemView.findViewById(R.id.sellRate);
            myText3 = itemView.findViewById(R.id.bankName);
            myImage = itemView.findViewById(R.id.bankLogo);
        }

    }
}
